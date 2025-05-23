# Copyright 2023 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

load("//build_defs/worker/gcp:deploy.bzl", "worker_gcp_deployment")

package(default_visibility = ["//visibility:public"])

worker_gcp_deployment(
    name = "worker_mp_gcp_prod",
    cmd = [
        "WorkerRunner_prod_deploy.jar",
        "--client_config_env",
        "GCP",
        "--job_client",
        "GCP",
        "--blob_storage_client",
        "GCP_CS_CLIENT",
        "--decryption_key_service",
        "GCP_KMS_MULTI_PARTY_DECRYPTION_KEY_SERVICE",
        "--primary_encryption_key_service_base_url",
        "https://privatekeyservice-a.msmt-3.gcp.privacysandboxservices.com/v1alpha",
        "--secondary_encryption_key_service_base_url",
        "https://privatekeyservice-b.msmt-4.gcp.privacysandboxservices.com/v1alpha",
        "--primary_encryption_key_service_cloudfunction_url",
        "https://a-us-central1-encryption-key-service-cloudfunctio-zihnau4cbq-uc.a.run.app",
        "--secondary_encryption_key_service_cloudfunction_url",
        "https://b-us-central1-encryption-key-service-cloudfunctio-mnlu5dzbga-uc.a.run.app",
        "--coordinator_a_kms_key",
        "gcp-kms://projects/ps-msmt-a-coord-prd-g3p/locations/us/keyRings/a_key_encryption_ring/cryptoKeys/a_key_encryption_key",
        "--coordinator_b_kms_key",
        "gcp-kms://projects/ps-prod-msmt-type2-e541/locations/us/keyRings/b_key_encryption_ring/cryptoKeys/b_key_encryption_key",
        "--coordinator_a_wip_provider",
        "projects/306633382134/locations/global/workloadIdentityPools/a-opwip/providers/a-opwip-pvdr",
        "--coordinator_a_sa",
        "a-opverifiedusr@ps-msmt-coord-prd-g3p-wif.iam.gserviceaccount.com",
        "--coordinator_b_wip_provider",
        "projects/364328752810/locations/global/workloadIdentityPools/b-opwip/providers/b-opwip-pvdr",
        "--coordinator_b_sa",
        "b-opverifiedusr@ps-prod-msmt-type2-e541.iam.gserviceaccount.com",
        "--coordinator_a_privacy_budgeting_service_base_url",
        "https://mp-pbs-a.msmt-3.gcp.privacysandboxservices.com/v1",
        "--coordinator_a_privacy_budgeting_service_auth_endpoint",
        "https://a-us-central1-pbs-auth-cloudfunction-zihnau4cbq-uc.a.run.app",
        "--coordinator_b_privacy_budgeting_service_base_url",
        "https://mp-pbs-b.msmt-4.gcp.privacysandboxservices.com/v1",
        "--coordinator_b_privacy_budgeting_service_auth_endpoint",
        "https://b-us-central1-pbs-auth-cloudfunction-mnlu5dzbga-uc.a.run.app",
        "--privacy_budgeting",
        "HTTP",
        "--param_client",
        "GCP",
        "--metric_client",
        "GCP",
        "--lifecycle_client",
        "GCP",
        "--pbs_client",
        "GCP",
        "--noising",
        "DP_NOISING",
        "--return_stack_trace",
        "--parallel_summary_upload_enabled",
        "--streaming_output_domain_processing_enabled",
        "--parallel_fact_noising_enabled",
        "--dont_consume_budget_in_debug_run_enabled",
        "--otel_logs_enabled",
    ],
    entrypoint = [
        "/usr/bin/java",
        "-XX:+ExitOnOutOfMemoryError",
        "-XX:MaxRAMPercentage=75.0",
        "-jar",
    ],
    files = [
        "//:generate_version_file",
        "//java/com/google/aggregate/adtech/worker/gcp:WorkerRunner_prod_deploy.jar",
        "//licenses/THIRD_PARTY_NOTICES/gcp_worker_mp_gcp_prod:licenses_tar",
    ],
    labels = {"tee.launch_policy.allow_cmd_override": "false"},
)
