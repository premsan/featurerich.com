CREATE TABLE "payment_payment_request" (
    "id" CHAR(36) NOT NULL,
    "version" BIGINT NOT NULL,
    "payment_id" CHAR(36) NOT NULL,
    "gateway_id" VARCHAR(256) NOT NULL,
    "gateway_attributes" VARCHAR(65535) NOT NULL,
    "updated_at" BIGINT NOT NULL,
    "updated_by" CHAR(36) NOT NULL,
    CONSTRAINT "payment_payment_request_pk" PRIMARY KEY ("id")
);

CREATE INDEX "payment_payment_request_payment_id_idx" ON "payment_payment_request" ("payment_id");