# AppBarTranslateScaleBehavior
---

A `CoordinatorLayout.Behavior<View>` which interacts with an `AppBarLayout` as a *dependency* for translating and scaling a specific `View` of your choice.

Watch how the [CircularImageView](https://github.com/lopspower/CircularImageView) translates and scales to be finally located in the `ToolBar`:

![alt text](yairVisitor.gif "Example")

### Usage

---

The following explanation uses the terms *source-view* and *destination-view*.

* *source-view* : The `View` on which the `Behavior` is attached representing the initial state - before translation and scaling.
* *destination-view* : The `View` to which the *source-view* will translate and scale - representing the final state of the *source-view*.

---

[ ![Download](https://api.bintray.com/packages/ranyalbegwein/AppBarTranslateScaleBehavior/appbar-translate-scale-behavior/images/download.svg) ](https://bintray.com/ranyalbegwein/AppBarTranslateScaleBehavior/appbar-translate-scale-behavior/_latestVersion)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-AppBarTranslateScaleBehavior-green.svg?style=flat )]( https://android-arsenal.com/details/1/6391 )

<a href='https://ko-fi.com/E1E0B4X4' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi4.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

1. Edit your *build.gradle* to include the library and sync.

```
dependencies {
    compile 'com.rany.albeg.wein:appbar-translate-scale-behavior:1.0.0'
}
```
Or with Maven:
```xml
<dependency>
  <groupId>com.rany.albeg.wein</groupId>
  <artifactId>appbar-translate-scale-behavior</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

2. Create a *destination-view* anywhere inside your `CoordinatorLayout` and give it an id. For example, in the preview above you can see that the final state of the profile image is a small icon (`32dp X 32dp`) located to the left of the `ToolBar`'s title, therefore the *destination-view*:

```xml
            <android.support.v7.widget.Toolbar
            	... >
				<!-- Destination View start -->
                <View
                    android:id="@+id/v_destination_profile_image"
                    android:layout_width="32dp"
                    android:layout_height="32dp"
                    android:visibility="invisible"/>
                <!-- Destination View end -->

            </android.support.v7.widget.Toolbar>
```

2. Attach the `Behavior` to a *source-view* of your choice and connect it with the *destination-view*, by adding the `app:layout_behavior` and `app:behavior_destination_id` attributes. For example, in the preview above I attached the `Behavior` to a [CircularImageView](https://github.com/lopspower/CircularImageView) representing the *source-view* which is connected to a *destination-view* with an *@id/v_destination_profile_image* like so:

```xml
    <com.mikhaellopez.circularimageview.CircularImageView
        android:layout_width="120dp"
        android:layout_height="100dp"
        ...
        app:behavior_destination_id="@id/v_destination_profile_image"
        app:layout_behavior="@string/appbar_translate_scale_behavior"/>
```

That's it, you're ready to go!

### Issues
* *destination-view* disappears:
	* **1** Set an *elevation* of 4dp + on *source-view*.
	
	OR
    
	* **2** Set *elevation* of 0dp on `AppBarLayout`.

AUTHOR
-------

**Rany Albeg Wein**


LICENSE
--------
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

