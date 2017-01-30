package aem.community.examples.codes.core.services.impl;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aem.community.examples.codes.core.services.ComponentDisablerDriver;

@Component
@Service
@Property(name="service.ranking", intValue={200})
public class ComponentDisablerDriverDS13
  implements ComponentDisablerDriver
{
  private static final Logger log = LoggerFactory.getLogger(ComponentDisabler.class);
  @Reference
  private ServiceComponentRuntime scr;
  private BundleContext bundleContext;
  
  public void disable(String componentName)
  {
    for (Bundle bundle : this.bundleContext.getBundles())
    {
      ComponentDescriptionDTO dto = this.scr.getComponentDescriptionDTO(bundle, componentName);
      if ((dto != null) && (this.scr.isComponentEnabled(dto)))
      {
        log.info("Component {} disabled by configuration.", dto.implementationClass);
        this.scr.disableComponent(dto);
      }
    }
  }
  
  @Activate
  public void activate(BundleContext bundleContext)
  {
    this.bundleContext = bundleContext;
  }
  
  protected void bindScr(ServiceComponentRuntime paramServiceComponentRuntime)
  {
    this.scr = paramServiceComponentRuntime;
  }
  
  protected void unbindScr(ServiceComponentRuntime paramServiceComponentRuntime)
  {
    if (this.scr == paramServiceComponentRuntime) {
      this.scr = null;
    }
  }
}
