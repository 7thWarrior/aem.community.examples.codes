/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package aem.community.y.examples.codes.core.schedulers;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple demo for cron-job like tasks that get executed regularly.
 * It also demonstrates how property values can be set. Users can
 * set the property values in /system/console/configMgr
 */
@Component(metatype = true, label = "Generic Schedular", 
    description = "Generic Schedular to schedule worflows using Quartz Syntax")
@Service(value = Runnable.class)
@Properties({
    @Property(name = "scheduler.expression", value = "*/30 * * * * ?",
        description = "Cron-job expression"),
    @Property(name = "scheduler.concurrent", boolValue=false,
        description = "Whether or not to schedule this task concurrently")
})
public class GenericWorkflowSchedular implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Property(label="Workflow ID",description="Workflow ID of targeted workflow which is being scheduled")
    public static final String WORKFLOW_ID="aem.community.example.simpleworkflowschedular.workflowId";
    private String workflowId;

    @Property(label = "Workflow Payload", description = "Payload to start targeted Worflow")
    public static final String WORKFLOW_PAYLOAD = "aem.community.example.simpleworkflowschedular.workflowpayload";
    private String payLoad;
    
    @Override
    public void run() {
        logger.debug("SimpleScheduledTask is now running, myParameter='{}'", workflowId);
    }
    

    
    @Activate
    protected void activate(final Map<String, Object> config) {
        configure(config);
    }

    private void configure(final Map<String, Object> config) {
        workflowId = PropertiesUtil.toString(config.get(WORKFLOW_ID), null);
        payLoad = PropertiesUtil.toString(config.get(WORKFLOW_PAYLOAD), null);
        logger.debug("configure: workflowId='{}''", workflowId);
        logger.debug("configure: payload='{}''", payLoad);
    }
}
