package com.nomad.PubGeoSun;

import com.luckycatlabs.sunrisesunset.Zenith;

import javax.baja.nre.annotations.*;
import javax.baja.sys.*;

@NiagaraType
public class BSunriseSunsetAstronomical extends BSunriseSunsetGeneric
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.nomad.PubGeoSun.BSunriseSunsetAstronomical(2979906276)1.0$ @*/
/* Generated Thu Apr 21 14:22:09 MDT 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(com.nomad.PubGeoSun.BSunriseSunsetAstronomical.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
  @Override
  public BIcon getIcon() {
    return BIcon.make("module://PubGeoSun/images/icon.png");
  }

  public BSunriseSunsetAstronomical() {
    super.MY_ZENITH = Zenith.ASTRONOMICAL;
    super.SUNRISE_EVENT = "Sunrise:Astronomical";
    super.SUNSET_EVENT = "Sunset:Astronomical";
  }
}
