package com.featurerich.application;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class FeatureController {

    private final ApplicationContext applicationContext;
    private final Map<String, List<Feature>> featureMap = new HashMap<>();

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {

        final Map<String, Object> controllers =
                applicationContext.getBeansWithAnnotation(Controller.class);

        for (final Object controller : controllers.values()) {

            final Class<?> controllerClass = AopUtils.getTargetClass(controller);
            for (final Method method : controllerClass.getDeclaredMethods()) {

                final FeatureMapping featureMapping = method.getAnnotation(FeatureMapping.class);
                final GetMapping featureGetMapping = method.getAnnotation(GetMapping.class);

                if (featureMapping == null || featureGetMapping == null) {

                    continue;
                }

                final PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);

                final Feature feature = new Feature();
                feature.setModule(featureMapping.module());
                feature.setPath(featureGetMapping.value()[0]);
                feature.setPriority(featureMapping.priority());
                feature.setMessageCode(controllerClass.getSimpleName().concat(".").concat(method.getName()));

                if (Objects.nonNull(preAuthorize)) {
                    feature.setPreAuthorizeValue(preAuthorize.value());
                }

                List<Feature> moduleFeatures = featureMap.get(featureMapping.module());
                if (moduleFeatures == null) {

                    moduleFeatures = new ArrayList<>();
                    featureMap.put(feature.getModule(), moduleFeatures);
                }
                moduleFeatures.add(feature);
            }
        }

        for (final List<Feature> moduleFeatures : featureMap.values()) {

            Collections.sort(moduleFeatures, Comparator.comparing(Feature::getPriority));
        }
    }

    @GetMapping("/application/application-feature-view")
    public ModelAndView applicationFeatureViewGet() {

        final ModelAndView modelAndView =
                new ModelAndView("com/featurerich/application/templates/application-restart");
        modelAndView.addObject("featureMap", featureMap);

        return modelAndView;
    }
}
