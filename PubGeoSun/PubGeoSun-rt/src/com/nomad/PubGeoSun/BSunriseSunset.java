package com.nomad.PubGeoSun;

import com.luckycatlabs.sunrisesunset.Zenith;

import javax.baja.nre.annotations.*;
import javax.baja.sys.*;
import javax.baja.units.BUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

@NiagaraProperty( name = "latitude", type = "BDouble", defaultValue = "BDouble.make(37.9308)", flags= Flags.SUMMARY,
    facets = {
      @Facet(name = "\"units\"", value = "BUnit.getUnit(\"null\")"),
      @Facet(name = "\"precision\"", value = "BInteger.make(4)")
} )

@NiagaraProperty( name = "longitude", type = "BDouble", defaultValue = "BDouble.make(-104.8786)", flags= Flags.SUMMARY,
    facets = {
      @Facet(name = "\"units\"", value = "BUnit.getUnit(\"null\")"),
      @Facet(name = "\"precision\"", value = "BInteger.make(4)")
} )

@NiagaraProperty( name = "sunrise", type = "BAbsTime", defaultValue = "BAbsTime.make()", flags= Flags.SUMMARY|Flags.READONLY|Flags.TRANSIENT )
@NiagaraProperty( name = "sunset", type = "BAbsTime", defaultValue = "BAbsTime.make()", flags= Flags.SUMMARY|Flags.READONLY|Flags.TRANSIENT )

@NiagaraTopic(name = "sunriseTopic", eventType = "BString", flags = Flags.SUMMARY)
@NiagaraTopic(name = "sunsetTopic", eventType = "BString", flags = Flags.SUMMARY)

@NiagaraType
public class BSunriseSunset extends BComponent
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.nomad.PubGeoSun.BSunriseSunset(1547362550)1.0$ @*/
/* Generated Mon Apr 24 13:52:55 MDT 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "latitude"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code latitude} property.
   * @see #getLatitude
   * @see #setLatitude
   */
  public static final Property latitude = newProperty(Flags.SUMMARY, ((BDouble)(BDouble.make(37.9308))).getDouble(), BFacets.make(BFacets.make("units", BUnit.getUnit("null")), BFacets.make("precision", BInteger.make(4))));
  
  /**
   * Get the {@code latitude} property.
   * @see #latitude
   */
  public double getLatitude() { return getDouble(latitude); }
  
  /**
   * Set the {@code latitude} property.
   * @see #latitude
   */
  public void setLatitude(double v) { setDouble(latitude, v, null); }

////////////////////////////////////////////////////////////////
// Property "longitude"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code longitude} property.
   * @see #getLongitude
   * @see #setLongitude
   */
  public static final Property longitude = newProperty(Flags.SUMMARY, ((BDouble)(BDouble.make(-104.8786))).getDouble(), BFacets.make(BFacets.make("units", BUnit.getUnit("null")), BFacets.make("precision", BInteger.make(4))));
  
  /**
   * Get the {@code longitude} property.
   * @see #longitude
   */
  public double getLongitude() { return getDouble(longitude); }
  
  /**
   * Set the {@code longitude} property.
   * @see #longitude
   */
  public void setLongitude(double v) { setDouble(longitude, v, null); }

////////////////////////////////////////////////////////////////
// Property "sunrise"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code sunrise} property.
   * @see #getSunrise
   * @see #setSunrise
   */
  public static final Property sunrise = newProperty(Flags.SUMMARY | Flags.READONLY | Flags.TRANSIENT, BAbsTime.make(), null);
  
  /**
   * Get the {@code sunrise} property.
   * @see #sunrise
   */
  public BAbsTime getSunrise() { return (BAbsTime)get(sunrise); }
  
  /**
   * Set the {@code sunrise} property.
   * @see #sunrise
   */
  public void setSunrise(BAbsTime v) { set(sunrise, v, null); }

////////////////////////////////////////////////////////////////
// Property "sunset"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code sunset} property.
   * @see #getSunset
   * @see #setSunset
   */
  public static final Property sunset = newProperty(Flags.SUMMARY | Flags.READONLY | Flags.TRANSIENT, BAbsTime.make(), null);
  
  /**
   * Get the {@code sunset} property.
   * @see #sunset
   */
  public BAbsTime getSunset() { return (BAbsTime)get(sunset); }
  
  /**
   * Set the {@code sunset} property.
   * @see #sunset
   */
  public void setSunset(BAbsTime v) { set(sunset, v, null); }

////////////////////////////////////////////////////////////////
// Topic "sunriseTopic"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code sunriseTopic} topic.
   * @see #fireSunriseTopic
   */
  public static final Topic sunriseTopic = newTopic(Flags.SUMMARY, null);
  
  /**
   * Fire an event for the {@code sunriseTopic} topic.
   * @see #sunriseTopic
   */
  public void fireSunriseTopic(BString event) { fire(sunriseTopic, event, null); }

////////////////////////////////////////////////////////////////
// Topic "sunsetTopic"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code sunsetTopic} topic.
   * @see #fireSunsetTopic
   */
  public static final Topic sunsetTopic = newTopic(Flags.SUMMARY, null);
  
  /**
   * Fire an event for the {@code sunsetTopic} topic.
   * @see #sunsetTopic
   */
  public void fireSunsetTopic(BString event) { fire(sunsetTopic, event, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSunriseSunset.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
  private SolarVector solarVector;
  private Timer eventPublisher;
  private final String MODULE_NAME = this.getType().getModule().getModuleName();
  private Logger log = Logger.getLogger(MODULE_NAME);

  @Override
  public void started() throws Exception {
    super.started();
    solarVector = new SolarVector(getLatitude(), getLongitude());
  }

  @Override
  public void stopped() throws Exception {
    if (null != eventPublisher) eventPublisher.cancel();
    eventPublisher = null;
    super.stopped();
  }

  @Override
  public void changed(Property property, Context context) {
    super.changed(property, context);
    if (property == latitude || property == longitude) {
      solarVector = new SolarVector(getLatitude(), getLongitude());
    }
  }

  protected void Reset(Zenith zenith) {
    if (null == solarVector) return;
    setSunrise(solarVector.getSunrise(zenith));
    setSunset(solarVector.getSunset(zenith));
  }

  protected void ResetEventTimers(String sunriseEventMessage, String sunsetEventMessage) {
    if (null != eventPublisher) eventPublisher.cancel();
    eventPublisher = new Timer("SunriseSunsetEventPublisher", true);
    scheduleSunrise(sunriseEventMessage);
    scheduleSunset(sunsetEventMessage);
  }

  private void scheduleSunrise(String sunriseEventMessage) {
    if (getSunrise().getMillis() < BAbsTime.now().getMillis()) return;
    eventPublisher.schedule(new TimerTask() {
      @Override public void run() {
        fireSunriseTopic(BString.make(sunriseEventMessage));
      }}, new Date(getSunrise().getMillis()));
  }

  private void scheduleSunset(String sunsetEventMessage) {
    if (getSunset().getMillis() < BAbsTime.now().getMillis()) return;
    eventPublisher.schedule(new TimerTask() {
      @Override public void run() {
        fireSunsetTopic(BString.make(sunsetEventMessage));
      }}, new Date(getSunset().getMillis()));
  }
}
