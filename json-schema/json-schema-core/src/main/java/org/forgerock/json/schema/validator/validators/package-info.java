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
 * Copyright 2011-2016 ForgeRock AS.
 * Portions Copyright 2021 Wren Security
 */

/**
 * These classes are capable of validating the objects against the initially loaded schema.
 * <p/>
 * All class implements the abstract {@link Validator} class and has the {@link Validator#Validator(java.util.Map, java.util.List)}}
 * constructor.
 * <p/>
 * Each class is responsible for validating one object type. See the mappings between the types and classes in the
 * this table.
 * <table>
 * 	   <caption>JSON type validators</caption>
 *     <tr>
 *         <td>string</td><td>{@link StringTypeValidator}</td>
 *     </tr>
 *     <tr>
 *         <td>number</td><td>{@link NumberTypeValidator}</td>
 *     </tr>
 *     <tr>
 *         <td>integer</td><td>{@link IntegerTypeValidator}</td>
 *     </tr>
 *     <tr>
 *         <td>boolean</td><td>{@link BooleanTypeValidator}</td>
 *     </tr>
 *     <tr>
 *         <td>object</td><td>{@link ObjectTypeValidator}</td>
 *     </tr>
 *     <tr>
 *         <td>array</td><td>{@link ArrayTypeValidator}</td>
 *     </tr>
 *     <tr>
 *         <td>null</td><td>{@link NullTypeValidator}</td>
 *     </tr>
 *     <tr>
 *         <td>any</td><td>{@link AnyTypeValidator}</td>
 *     </tr>
 *     <tr>
 *         <td>Union Types</td><td>{@link UnionTypeValidator}</td>
 *     </tr>
 * </table>
 * <p/>
 * Other <a href="http://tools.ietf.org/html/draft-zyp-json-schema-03#section-5.1">type</a> values MAY be used
 * for custom purposes, but minimal validators of the specification implementation can allow any instance value
 * on unknown type values.
 */
package org.forgerock.json.schema.validator.validators;
