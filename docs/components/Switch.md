---
layout: detail
title: Switch
description: Switch description
---

# Selection controls: switches

Selection controls allow the user to select options.

Switches toggle the state of a single setting on or off. They are the preferred
way to adjust settings on mobile.

**Contents**

*   [Using switches](#using-switches)

## Using switches

Before you can use Orange themed switches, you need to add a dependency to the Orange Design
System for Android library. For more information, go to the
[Getting started](../getting-started.md) page.

### Material Design

Orange Switches are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Checkbox](https://material.io/components/switches/)

### Accessibility

Switches support content labeling for accessibility and are readable by most
screen readers, such as TalkBack. Text rendered in switches is automatically
provided to accessibility services. Additional content labels are usually
unnecessary.

### Adding a switch

To create a Switch using Orange theme you will have to add `com.google.android.material.switchmaterial.SwitchMaterial` into your layout.

*   `SwitchMaterial`
    *   [Class definition](https://developer.android.com/reference/com/google/android/material/switchmaterial/SwitchMaterial)
    *   [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/switchmaterial/SwitchMaterial.java)

In the layout:

```xml
<com.google.android.material.switchmaterial.SwitchMaterial
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:checked="true"
    android:text="@string/label_1"
/>
<com.google.android.material.switchmaterial.SwitchMaterial
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:text="@string/label_2"
/>
<com.google.android.material.switchmaterial.SwitchMaterial
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:text="@string/label_3"
/>
<com.google.android.material.switchmaterial.SwitchMaterial
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:text="@string/label_4"
/>
<com.google.android.material.switchmaterial.SwitchMaterial
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:enabled="false"
    android:text="@string/label_5"
/>
```

In code:

```kt
// To check a switch
switchMaterial.isChecked = true

// To listen for a switch's checked/unchecked state changes
switchMaterial.setOnCheckedChangeListener { buttonView, isChecked
    // Responds to switch being checked/unchecked
}
```