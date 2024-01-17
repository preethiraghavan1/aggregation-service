/*
 * Copyright 2022 Google LLC
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

package com.google.aggregate.adtech.worker.encryption.hybrid.key.cloud;

import com.google.aggregate.adtech.worker.encryption.hybrid.key.EncryptionKeyService;
import com.google.crypto.tink.KeysetHandle;
import com.google.inject.AbstractModule;

/** Guice Module that provides the {@link KeysetHandle} from cloud public key vending. */
public final class CloudEncryptionKeyModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(EncryptionKeyService.class).to(CloudEncryptionKeyService.class);
  }
}
