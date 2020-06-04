/*-
 * ========================LICENSE_START=================================
 * smooks-edi-cartridge
 * %%
 * Copyright (C) 2020 Smooks
 * %%
 * Licensed under the terms of the Apache License Version 2.0, or
 * the GNU Lesser General Public License version 3.0 or later.
 * 
 * SPDX-License-Identifier: Apache-2.0 OR LGPL-3.0-or-later
 * 
 * ======================================================================
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * ======================================================================
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * =========================LICENSE_END==================================
 */
package org.smooks.cartridges.edi;

import org.apache.daffodil.japi.ValidationMode;
import org.smooks.GenericReaderConfigurator;
import org.smooks.ReaderConfigurator;
import org.smooks.assertion.AssertArgument;
import org.smooks.cartridges.dfdl.parser.DfdlParser;
import org.smooks.cartridges.dfdl.parser.DfdlReaderConfigurator;
import org.smooks.cdr.Parameter;
import org.smooks.cdr.SmooksResourceConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdiReaderConfigurator extends DfdlReaderConfigurator {

    protected String segmentTerminator = "'%NL;%WSP*; '%WSP*;";
    protected String dataElementSeparator = "+";
    protected String compositeDataElementSeparator = ":";
    protected String escapeCharacter = "?";
    protected String repetitionSeparator ="*";
    protected String decimalSign = ".";
    protected String triadSeparator = ",";

    public EdiReaderConfigurator(final String schemaUri) {
        super(schemaUri);
    }

    public EdiReaderConfigurator setSegmentTerminator(String segmentTerminator) {
        AssertArgument.isNotNullAndNotEmpty(segmentTerminator, "segmentTerminator");
        this.segmentTerminator = segmentTerminator;
        return this;
    }

    public EdiReaderConfigurator setDataElementSeparator(String dataElementSeparator) {
        AssertArgument.isNotNullAndNotEmpty(dataElementSeparator, "dataElementSeparator");
        this.dataElementSeparator = dataElementSeparator;
        return this;
    }

    public EdiReaderConfigurator setCompositeDataElementSeparator(String compositeDataElementSeparator) {
        AssertArgument.isNotNullAndNotEmpty(compositeDataElementSeparator, "compositeDataElementSeparator");
        this.compositeDataElementSeparator = compositeDataElementSeparator;
        return this;
    }

    public EdiReaderConfigurator setEscapeCharacter(String escapeCharacter) {
        AssertArgument.isNotNullAndNotEmpty(escapeCharacter, "escapeCharacter");
        this.escapeCharacter = escapeCharacter;
        return this;
    }

    public EdiReaderConfigurator setRepetitionSeparator(String repetitionSeparator) {
        AssertArgument.isNotNullAndNotEmpty(repetitionSeparator, "repetitionSeparator");
        this.repetitionSeparator = repetitionSeparator;
        return this;
    }

    public EdiReaderConfigurator setDecimalSign(String decimalSign) {
        AssertArgument.isNotNullAndNotEmpty(decimalSign, "decimalSign");
        this.decimalSign = decimalSign;
        return this;
    }

    public EdiReaderConfigurator setTriadSeparator(String triadSeparator) {
        AssertArgument.isNotNullAndNotEmpty(triadSeparator, "triadSeparator");
        this.triadSeparator = triadSeparator;
        return this;
    }

    @Override
    protected String getDataProcessorFactory() {
        return "org.smooks.cartridges.edi.EdiDataProcessorFactory";
    }

    @Override
    public List<SmooksResourceConfiguration> toConfig() {
        final List<SmooksResourceConfiguration> smooksResourceConfigurations = super.toConfig();
        final SmooksResourceConfiguration smooksResourceConfiguration = smooksResourceConfigurations.get(0);

        smooksResourceConfiguration.setParameter(new Parameter("dataProcessorFactory", getDataProcessorFactory()));
        smooksResourceConfiguration.setParameter(new Parameter("segmentTerminator", segmentTerminator));
        smooksResourceConfiguration.setParameter(new Parameter("dataElementSeparator", dataElementSeparator));
        smooksResourceConfiguration.setParameter(new Parameter("compositeDataElementSeparator", compositeDataElementSeparator));
        smooksResourceConfiguration.setParameter(new Parameter("escapeCharacter", escapeCharacter));
        smooksResourceConfiguration.setParameter(new Parameter("repetitionSeparator", repetitionSeparator));
        smooksResourceConfiguration.setParameter(new Parameter("decimalSign", decimalSign));
        smooksResourceConfiguration.setParameter(new Parameter("triadSeparator", triadSeparator));

        return smooksResourceConfigurations;
    }
}
