package aem.community.examples.codes.core.services.impl;

import java.util.Arrays;
import java.util.Map;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferencePolicyOption;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aem.community.examples.codes.core.services.ComponentDisablerDriver;

@Component(immediate=true, metatype=true, label="Warrior - OSGI Component Disabler", description="Disables components by configuration", policy=ConfigurationPolicy.REQUIRE)
@Service
@Property(name="event.topics", value={"org/osgi/framework/BundleEvent/STARTED", "org/osgi/framework/ServiceEvent/REGISTERED"}, propertyPrivate=true)
public class ComponentDisabler
  implements EventHandler
{
  private static final Logger log = LoggerFactory.getLogger(ComponentDisabler.class);
  @Reference(policyOption=ReferencePolicyOption.GREEDY)
  private ComponentDisablerDriver componentDisabler;
  @Property(label="Disabled components", description="The names of the components/services you want to disable", cardinality=Integer.MAX_VALUE)
  private static final String DISABLED_COMPONENTS = "components";
  private String[] disabledComponents;
  
  @Activate
  protected void activate(Map<String, Object> properties)
  {
    this.disabledComponents = PropertiesUtil.toStringArray(properties.get("components"), new String[0]);
    handleEvent(null);
  }
  
  public void handleEvent(Event event)
  {
    log.trace("Disabling components and services {}", Arrays.toString(this.disabledComponents));
    for (String component : this.disabledComponents) {
      this.componentDisabler.disable(component);
    }
  }
  
  protected void bindComponentDisabler(ComponentDisablerDriver paramComponentDisablerDriver)
  {
    this.componentDisabler = paramComponentDisablerDriver;
  }
  
  protected void unbindComponentDisabler(ComponentDisablerDriver paramComponentDisablerDriver)
  {
    if (this.componentDisabler == paramComponentDisablerDriver) {
      this.componentDisabler = null;
    }
  }
}
