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
 * Copyright 2016 ForgeRock AS.
 */

package org.forgerock.api.jackson;

import javax.validation.ValidationException;

import org.forgerock.json.JsonValue;

import com.fasterxml.jackson.module.jsonSchema.types.AnySchema;
import org.forgerock.api.enums.WritePolicy;

/**
 * An extension to the Jackson {@code AnySchema} that includes the custom CREST JSON Schema attributes.
 */
public class CrestAnySchema extends AnySchema implements CrestReadWritePoliciesSchema, OrderedFieldSchema,
        ValidatableSchema {
    private WritePolicy writePolicy;
    private Boolean errorOnWritePolicyFailure;
    private Integer propertyOrder;

    @Override
    public WritePolicy getWritePolicy() {
        return writePolicy;
    }

    @Override
    public void setWritePolicy(WritePolicy policy) {
        this.writePolicy = policy;
    }

    @Override
    public Boolean getErrorOnWritePolicyFailure() {
        return errorOnWritePolicyFailure;
    }

    @Override
    public void setErrorOnWritePolicyFailure(Boolean errorOnWritePolicyFailure) {
        this.errorOnWritePolicyFailure = errorOnWritePolicyFailure;
    }

    @Override
    public Integer getPropertyOrder() {
        return propertyOrder;
    }

    @Override
    public void setPropertyOrder(Integer order) {
        this.propertyOrder = order;
    }

    @Override
    public void validate(JsonValue object) throws ValidationException {
        // any Object is a valid AnySchema.
    }
}