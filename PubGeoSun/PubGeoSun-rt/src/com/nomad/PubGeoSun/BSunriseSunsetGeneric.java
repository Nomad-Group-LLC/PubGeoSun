package com.nomad.PubGeoSun;

import com.luckycatlabs.sunrisesunset.Zenith;

import javax.baja.nre.annotations.*;
import javax.baja.sys.*;
import java.util.Timer;
import java.util.TimerTask;

@NiagaraAction(name = "Reset", flags = Flags.SUMMARY|Flags.OPERATOR)

@NiagaraType
public class BSunriseSunsetGeneric extends BSunriseSunset
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.nomad.PubGeoSun.BSunriseSunsetGeneric(3100231250)1.0$ @*/
/* Generated Thu Apr 21 19:24:48 MDT 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Action "Reset"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code Reset} action.
   * @see #Reset()
   */
  public static final Action Reset = newAction(Flags.SUMMARY | Flags.OPERATOR, null);
  
  /**
   * Invoke the {@code Reset} action.
   * @see #Reset
   */
  public void Reset() { invoke(Reset, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSunriseSunsetGeneric.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  protected Zenith MY_ZENITH;
  protected String SUNRISE_EVENT;
  protected String SUNSET_EVENT;

  private Timer refreshTimer;
  private final long MS_ONE_HOUR = 60*(60*1000);

  @Override public void started() throws Exception {
    super.started();
    refreshTimer = new Timer("NGSunriseSunsetRefreshTimer", true);
    refreshTimer.scheduleAtFixedRate(new TimerTask() {
      @Override public void run() { reset(); }
    }, 2000, MS_ONE_HOUR);
  }

  @Override public void stopped() {
    if (null != refreshTimer) {
      refreshTimer.cancel();
    }
  }

  @Override public void changed(Property property, Context context) {
    super.changed(property, context);
    if(context == Context.decoding) return;
    if (property == latitude || property == longitude) reset();
  }

  public void doReset() {
    reset();
  }

  private void reset() {
    if (null == MY_ZENITH) return;
    super.Reset(MY_ZENITH);
    super.ResetEventTimers(SUNRISE_EVENT, SUNSET_EVENT);
  }
}
