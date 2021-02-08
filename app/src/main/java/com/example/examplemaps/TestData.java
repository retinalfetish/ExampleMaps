/*
 * Copyright 2020 Christopher Zaborsky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.examplemaps;

import android.location.Location;

public class TestData {

    public static Location[] getCluster(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Location[] dwarfs = new Location[7];

        dwarfs[0] = new Location("Bashful");
        dwarfs[1] = new Location("Doc");
        dwarfs[2] = new Location("Dopey");
        dwarfs[3] = new Location("Grumpy");
        dwarfs[4] = new Location("Happy");
        dwarfs[5] = new Location("Sleepy");
        dwarfs[6] = new Location("Sneezy");

        dwarfs[0].setLatitude(latitude - 0.004);
        dwarfs[0].setLongitude(longitude - 0.004);

        dwarfs[1].setLatitude(latitude - 0.006);
        dwarfs[1].setLongitude(longitude - 0.006);

        dwarfs[2].setLatitude(latitude - 0.012);
        dwarfs[2].setLongitude(longitude - 0.012);

        dwarfs[3].setLatitude(latitude + 0.002);
        dwarfs[3].setLongitude(longitude + 0.002);

        dwarfs[4].setLatitude(latitude + 0.008);
        dwarfs[4].setLongitude(longitude + 0.008);

        dwarfs[5].setLatitude(latitude + 0.010);
        dwarfs[5].setLongitude(longitude - 0.010);

        dwarfs[6].setLatitude(latitude - 0.014);
        dwarfs[6].setLongitude(longitude + 0.014);

        return dwarfs;
    }
}