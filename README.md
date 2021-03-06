# SwipeSelector
<img src="https://raw.githubusercontent.com/roughike/SwipeSelector/master/demo_two.gif" width="278" height="492" />

## THIS VERSION IS FORKED FROM roughike check out the source here https://github.com/roughike/SwipeSelector

## What and why?

Bored of dull looking radio buttons and dropdowns? Me too. I started looking for a more sophisticated way of offering user a choice, and came up with [this beautiful dribble](https://dribbble.com/shots/2343630-Create-Shipment).

Unfortunately, there were no ready-made solutions to achieve this, so I spent a good day working on this very thing I call SwipeSelector.

## minSDK Version

SwipeSelector supports API levels all the way down to 9 (Android Gingerbread).

### NOTE: I'm waiting Sonatype response for deploying the library. Be patient please, everything will be set up as soon as possible!

## Installation
**Gradle:**

```groovy
compile 'com.github.antoniodisanto92:swipe-selector:1.0.0'
```

**Maven:**
```xml
<dependency>
  <groupId>com.github.antoniodisanto92</groupId>
  <artifactId>swipe-selector</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

## How do I use it?

The usage is really simple.

**First add SwipeSelector to your layout file:**

```xml
<com.github.antoniodisanto92.swipeselector.SwipeSelector
    android:id="@+id/swipeSelector"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

**Then get a hold of it and give it a set of SwipeItem objects with values, titles and descriptions:**

```java
SwipeSelector swipeSelector = (SwipeSelector) findViewById(R.id.swipeSelector);
swipeSelector.setItems(
  // The first argument is the value for that item, and should in most cases be unique for the
  // current SwipeSelector, just as you would assign values to radio buttons.
  // You can use the value later on to check what the selected item was.
  // The value can be any Object, here we're using ints.
  new SwipeItem(0, "Slide one", "Description for slide one."),
  new SwipeItem(1, "Slide two", "Description for slide two."),
  new SwipeItem(2, "Slide three", "Description for slide three.")
);
```

**Whenever you need to know what is the currently showing SwipeItem:**
```java
SwipeItem selectedItem = swipeSelector.getSelectedItem();

// The value is the first argument provided when creating the SwipeItem.
int value = (Integer) selectedItem.value;

// for example
if (value == 0) {
  // The user selected slide number one.
}
```

For an example project using multiple SwipeSelectors, [refer to the sample app](https://github.com/antoniodisanto92/SwipeSelector/tree/master/sample/src/main).

## Customization

```xml
<com.github.antoniodisanto92.swipeselector.SwipeSelector
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/conditionSelector"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:swipe_indicatorSize="10dp"
    app:swipe_indicatorMargin="12dp"
    app:swipe_indicatorInActiveColor="#DDDDDD"
    app:swipe_indicatorActiveColor="#FF00FF"
    app:swipe_leftButtonResource="@drawable/leftButtonResource"
    app:swipe_rightButtonResource="@drawable/rightButtonResource"
    app:swipe_customFontPath="fonts/MySuperDuperFont.ttf"
    app:swipe_titleTextAppearance="@style/MyTitleTextApperance"
    app:swipe_descriptionTextAppearance="@style/MyDescriptionTextApperance"
    app:swipe_descriptionGravity="center" />
```

<dl>
  <dt>swipe_indicatorSize</dt>
  <dd>the size for the circle indicators.</dd>

  <dt>swipe_indicatorMargin</dt>
  <dd>how far the indicators are from each other.</dd>

  <dt>swipe_indicatorInActiveColor</dt>
  <dd>the color for normal unselected indicators.</dd>

  <dt>swipe_indicatorActiveColor</dt>
  <dd>the color for selected indicator.</dd>

  <dt>swipe_leftButtonResource and swipe_rightButtonResource</dt>
  <dd>custom Drawable resources for the left and right buttons. The margins for the content are calculated automatically, so even a bigger custom image won't overlap the content.</dd>

  <dt>swipe_customFontPath</dt>
  <dd>path for your custom font file, such as <code>fonts/MySuperDuperFont.ttf</code>. In that case your font path would look like <code>src/main/assets/fonts/MySuperDuperFont.ttf</code>, but you only need to provide <code>fonts/MySuperDuperFont.ttf</code>, as the asset folder will be auto-filled for you.</dd>

  <dt>swipe_titleTextAppearance and swipe_descriptionTextAppearance</dt>
  <dd>custom TextAppearance for the title and description TextViews for modifying the font sizes and colors and what not.</dd>
  
  <dt>swipe_descriptionGravity</dt>
  <dd>custom horizontal gravity (in other words alignment) for the description text. Can be either <code>left</code>, <code>center</code> or <code>right</code>. Default should be fine in most cases, but sometimes you might need to modify this.</dd>
</dl>

## Implementations

```SlideItem Class

In constructor now is possible to define if there is an Icon and select the position in layout (Can be either <code>SwipeIconGravity.DEFAULT</code>, <code>SwipeIconGravity.CENTER</code>, <code>SwipeIconGravity.LEFT</code>, <code>SwipeIconGravity.RIGHT</code> -> default and center have the same effect).

There is also the support to change Gravity (alignment) for Title and Description independently to <code>swipe_descriptionGravity</code> (this mean that the gravity is changed for the Item only, instead of the whole selector) value inside the SwipeSelector class.
To control the behaviour described before, the SwipeItem have now two setters <code>setTitleGravity(SwipeIconGravity gravity)</code> and <code>setDescriptionGravity(SwipeIconGravity gravity)</code>.

```

## Apps using SwipeSelector

  * [ScoreIt - Score Keeper](https://play.google.com/store/apps/details?id=com.sbgapps.scoreit) : An application to keep track of score.

Send me a pull request with modified README.md to get a shoutout!

## Contributions

Feel free to create issues / pull requests.

## License

```
SwipeSelector Implementation library for Android
Copyright (c) 2016 Antonio D'Isanto (http://github.com/antoniodisanto92).

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
