package com.nomad.PubGeoSun;

import com.luckycatlabs.sunrisesunset.Zenith;

import javax.baja.nre.annotations.*;
import javax.baja.sys.*;

@NiagaraType
public class BSunriseSunsetNautical extends BSunriseSunsetGeneric
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.nomad.PubGeoSun.BSunriseSunsetNautical(2979906276)1.0$ @*/
/* Generated Thu Apr 21 13:23:34 MDT 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(com.nomad.PubGeoSun.BSunriseSunsetNautical.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
  @Override
  public BIcon getIcon() {
    return BIcon.make("module://PubGeoSun/images/icon.png");
  }

  public BSunriseSunsetNautical() {
    super.MY_ZENITH = Zenith.NAUTICAL;
    super.SUNRISE_EVENT = "Sunrise:Nautical";
    super.SUNSET_EVENT = "Sunset:Nautical";
  }
}
