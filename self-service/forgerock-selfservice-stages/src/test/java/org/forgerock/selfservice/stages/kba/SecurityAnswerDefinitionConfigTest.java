/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2015 ForgeRock AS.
 */
package org.forgerock.selfservice.stages.kba;


import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import org.forgerock.selfservice.core.config.StageConfig;
import org.testng.annotations.Test;

/**
 * Unit test for {@link SecurityAnswerDefinitionConfig}.
 *
 * @since 0.2.0
 */
public final class SecurityAnswerDefinitionConfigTest {

    @Test
    public void testConfigFromJson() throws Exception {
        ObjectMapper mapper = JsonMapper.builder()
                .registerSubtypes(new NamedType(SecurityAnswerDefinitionConfig.class, SecurityAnswerDefinitionConfig.NAME))
                .build();
        StageConfig config = mapper.readValue(getClass().getResource("/kbaSecurityAnswerDefinition.json"),
                StageConfig.class);

        assertThat(config).isInstanceOf(SecurityAnswerDefinitionConfig.class);
        SecurityAnswerDefinitionConfig kbaConfig = (SecurityAnswerDefinitionConfig) config;
        assertThat(kbaConfig.getKbaPropertyName()).isEqualTo("kbaInfo");
        assertThat(kbaConfig.getQuestions().get("1").get("en")).isEqualTo("What's your favorite color?");
        assertThat(kbaConfig.getQuestions().get("1").get("en_GB")).isEqualTo("What's your favorite colour?");
        assertThat(kbaConfig.getQuestions().get("1").get("fr")).isEqualTo("Quelle est votre couleur préférée?");
        assertThat(kbaConfig.getQuestions().get("2").get("en")).isEqualTo("Who was your first employer?");
    }

}
