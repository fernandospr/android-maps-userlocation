Demonstrates how to use maps and the current user location using the `com.google.android.gms:play-services:9.8.0` dependency.

Includes two ways of getting the current user location:

1. Using `LocationServices.FusedLocationApi.requestLocationUpdates`
2. Using `googleMap.setOnMyLocationChangeListener` (deprecated)

#### Usage ####

To start the app, first you will need an Android key and place it in `google_maps_api.xml`.

After starting the app, choose the desired approach to obtain the user location.

#### Memory leak ####

The project also demonstrates a leak using the first approach. 

To detect memory leaks, I'm using [LeakCanary](https://github.com/square/leakcanary).

LeakCanary detects a memory leak after calling LocationServices.FusedLocationApi.requestLocationUpdates.

To reproduce:

1. Start the app
2. Choose LocationServices.FusedLocationApi button
3. Start Location Updates
4. Press back twice to exit the app
5. Wait for LeakCanary to show the leak

Bug reports:

https://code.google.com/p/android/issues/detail?id=227856

https://github.com/googlesamples/android-play-location/issues/26
