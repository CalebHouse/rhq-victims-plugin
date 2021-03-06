/*
 * RHQ Management Platform
 * Copyright (C) 2005-2008 Red Hat, Inc.
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */
package org.rhq.plugins.victims;

import java.util.Set;
import java.util.Collections;

import org.rhq.core.domain.configuration.Configuration;
import org.rhq.core.pluginapi.inventory.DiscoveredResourceDetails;
import org.rhq.core.pluginapi.inventory.InvalidPluginConfigurationException;
import org.rhq.core.pluginapi.inventory.ManualAddFacet;
import org.rhq.core.pluginapi.inventory.ResourceDiscoveryComponent;
import org.rhq.core.pluginapi.inventory.ResourceDiscoveryContext;

/**
* 
* @author Caleb House
*/
public class VictimsDiscoveryComponent implements ManualAddFacet, ResourceDiscoveryComponent {

	//Setup for the plugin. Since nothing happens just create a one time Discovered Resource.
    @Override
    public DiscoveredResourceDetails discoverResource(Configuration pluginConfiguration,
                                                      ResourceDiscoveryContext context) throws InvalidPluginConfigurationException {

    	String path = pluginConfiguration.getSimpleValue("path");
    	if (path==null || path.isEmpty()) {
            throw new InvalidPluginConfigurationException("Path must not be empty");
        }
        if (path.equals("/")) {
            throw new InvalidPluginConfigurationException("/ is forbidden");
        }
        
        DiscoveredResourceDetails result = new DiscoveredResourceDetails(
            context.getResourceType(),
            path,
            path,
            null,
            "Victims Paths",
            pluginConfiguration,
            null);
        return result;
    }

    //Not used but brought in from interfaces
    @Override
    public Set<DiscoveredResourceDetails> discoverResources(
        ResourceDiscoveryContext context) throws InvalidPluginConfigurationException, Exception {
        return Collections.emptySet();
    }
}