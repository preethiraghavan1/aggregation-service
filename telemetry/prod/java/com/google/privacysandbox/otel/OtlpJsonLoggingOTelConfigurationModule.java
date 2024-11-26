/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.privacysandbox.otel;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.exporter.logging.otlp.OtlpJsonLoggingMetricExporter;
import io.opentelemetry.exporter.logging.otlp.OtlpJsonLoggingSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.common.Clock;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import java.time.Duration;

/** Json Configuration module which would log the metric to std out. */
public final class OtlpJsonLoggingOTelConfigurationModule extends OTelConfigurationModule {
  @Provides
  @Singleton
  OTelConfiguration provideOtelConfig(Duration duration, Resource resource, Clock clock) {
    PeriodicMetricReader periodicMetricReader =
        PeriodicMetricReader.builder(OtlpJsonLoggingMetricExporter.create())
            .setInterval(duration)
            .build();
    OtlpJsonLoggingSpanExporter otlpJsonLoggingSpanExporter =
        (OtlpJsonLoggingSpanExporter) OtlpJsonLoggingSpanExporter.create();
    SdkTracerProvider sdkTracerProvider =
        SdkTracerProvider.builder()
            .addSpanProcessor(BatchSpanProcessor.builder(otlpJsonLoggingSpanExporter).build())
            .setResource(resource)
            .setClock(clock)
            .build();
    SdkMeterProvider sdkMeterProvider =
        SdkMeterProvider.builder()
            .setResource(resource)
            .setClock(clock)
            .registerMetricReader(periodicMetricReader)
            .build();
    OpenTelemetry oTel =
        OpenTelemetrySdk.builder()
            .setMeterProvider(sdkMeterProvider)
            .setTracerProvider(sdkTracerProvider)
            .build();
    return new OTelConfigurationImpl(oTel);
  }
}
