/*
 * Copyright 2017-2022 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.starter.feature.test;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.starter.application.ApplicationType;
import io.micronaut.starter.application.generator.GeneratorContext;
import io.micronaut.starter.build.dependencies.Dependency;
import io.micronaut.starter.feature.DefaultFeature;
import io.micronaut.starter.feature.Feature;
import io.micronaut.starter.options.BuildTool;
import io.micronaut.starter.options.Language;
import io.micronaut.starter.options.Options;
import jakarta.inject.Singleton;

import java.util.Set;

@Singleton
public class Mockk implements MockingFeature, DefaultFeature {
    public static final String ARTIFACT_ID_MOCKK = "mockk";
    public static final String NAME_MOCKK = "mockk";
    public static final String GROUP_ID_IO_MOCKK = "io.mockk";

    @Override
    @NonNull
    public String getName() {
        return NAME_MOCKK;
    }

    @Override
    public String getTitle() {
        return "Mockk";
    }

    @Override
    @NonNull
    public String getDescription() {
        return "Mocking library for Kotlin";
    }

    @Override
    public String getThirdPartyDocumentation() {
        return "https://mockk.io";
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        // Only for Maven, these dependencies are applied by the Micronaut Gradle Plugin
        if (generatorContext.getBuildTool() == BuildTool.MAVEN) {
            generatorContext.addDependency(Dependency.builder()
                    .lookupArtifactId(ARTIFACT_ID_MOCKK)
                    .test());
        }
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public boolean shouldApply(ApplicationType applicationType, Options options, Set<Feature> selectedFeatures) {
        return options.getBuildTool() == BuildTool.MAVEN &&
                options.getLanguage() == Language.KOTLIN &&
                options.getTestFramework().isKotlinTestFramework();
    }
}


