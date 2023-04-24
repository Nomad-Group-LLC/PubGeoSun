package com.nomad.PubGeoSun;

import com.luckycatlabs.sunrisesunset.Zenith;
import com.luckycatlabs.sunrisesunset.calculator.SolarEventCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import javax.baja.sys.BAbsTime;
import javax.baja.timezone.BTimeZone;
import java.util.Calendar;
import java.util.TimeZone;

final class SolarVector
{
  private final SolarEventCalculator calculator;
  private final TimeZone localTimeZone = BTimeZone.getLocal().getJavaTimeZone();

  public SolarVector(double latitude, double longitude) {
    calculator = new SolarEventCalculator(new Location(latitude, longitude), localTimeZone);
  }

  public BAbsTime getSunrise(Zenith zenith) {
    Calendar sunrise = calculator.computeSunriseCalendar(zenith, Calendar.getInstance(localTimeZone));
    return BAbsTime.make(sunrise.getTimeInMillis());
  }

  public BAbsTime getSunset(Zenith zenith) {
    Calendar sunset = calculator.computeSunsetCalendar(zenith, Calendar.getInstance(localTimeZone));
    return BAbsTime.make(sunset.getTimeInMillis());
  }
}
