package com.example.dummydashboard.utils

import com.example.dummydashboard.models.Customer

class DataSource {
    companion object {
        fun getDataSet(): ArrayList<Customer> {
            val list = ArrayList<Customer>()

            for (i in 1..100) {
                list.add(
                    Customer(
                        "$i. Jackson",
                        "https://media.istockphoto.com/photos/millennial-male-team-leader-organize-virtual-workshop-with-employees-picture-id1300972574?b=1&k=20&m=1300972574&s=170667a&w=0&h=2nBGC7tr0kWIU8zRQ3dMg-C5JLo9H2sNUuDjQ5mlYfo="
                    )
                )

                list.add(
                    Customer(
                        "$i. Amanda",
                        "https://media.istockphoto.com/photos/one-beautiful-woman-looking-at-the-camera-in-profile-picture-id1303539316?b=1&k=20&m=1303539316&s=170667a&w=0&h=ePGGvjsOR__-R2KSvZ67xXl2x-CkVzKg8q_WtvqLww0="
                    )
                )

                list.add(
                    Customer(
                        "$i. Jana",
                        "https://media.istockphoto.com/photos/overjoyed-pretty-asian-woman-look-at-camera-with-sincere-laughter-picture-id1311084168?b=1&k=20&m=1311084168&s=170667a&w=0&h=mE8BgXPgcHO1UjSmdWYa21NIKDzJvMrjOffy39Ritpo="
                    )
                )

                list.add(
                    Customer(
                        "$i. Ahmad",
                        "https://media.istockphoto.com/photos/headshot-portrait-of-smiling-ethnic-businessman-in-office-picture-id1300512215?b=1&k=20&m=1300512215&s=170667a&w=0&h=LsZL_-vvAHB2A2sNLHu9Vpoib_3aLLkRamveVW3AGeQ="
                    )
                )

                list.add(
                    Customer(
                        "$i. Alison",
                        "https://media.istockphoto.com/photos/middle-age-man-portrait-picture-id1285124274?b=1&k=20&m=1285124274&s=170667a&w=0&h=tdCWjbu8NxR_vhU3Tri7mZcfUH6WdcYWS1aurF4bbKI="
                    )
                )

                list.add(
                    Customer(
                        "$i. Kon",
                        "https://images.unsplash.com/photo-1531427186611-ecfd6d936c79?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZmlsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"
                    )
                )
            }

            return list
        }
    }
}