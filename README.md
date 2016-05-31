# StylishWidget

**StylishWidget** is a library for an **Android Application project** to make the **UI more beautiful** and use **third party font**.
This library I use in my previous and current android project and may got an issue and error. 
I will keep improve this library until it stable and useful.

![Screenshot](https://github.com/shiburagi/Stylish-Widget/blob/master/device-2016-05-29-210824.png)

## Features
 * set custom font for almost all view.
 * Message Box

Android 7.0+ support

## Download
 * **JAR** : (https://github.com/shiburagi/Stylish-Widget/blob/master/stylishwidget/libs/stylishwidget1.0.jar?raw=true)
 * **APK** : (https://drive.google.com/file/d/0Bw_drx3o3plaZVptWWNxWUdfSmM/view?usp=sharing) 

## Including In Your Project
This library is presented as a `.jar` file which you can include in the `libs/`
folder of your application. You can download the latest version from the
[github repo](https://github.com/shiburagi/Stylish-Widget/blob/master/stylishwidget/libs/stylishwidget1.0.jar?raw=true).

**or**,
you can include it by **download this project** and **import /stylishwidget** as **module**.

## Usage

**NOTE:**　**Initialize** this line of code at the **top of onCreate() in main activity**, or at the **onCreate() of Custom** Application.

``` java
// parameter : Normal Font, Bold Font and Italic Font
Stylish.getInstance().set(
                fontFolder.concat("Rajdhani-Regular.ttf"),
                fontFolder.concat("Rajdhani-Bold.ttf"),
                fontFolder.concat("Rajdhani-Light.ttf"));
```
**or**
``` java
// parameter : Normal Font
Stylish.getInstance().setFontRegular(
                fontFolder.concat("Rajdhani-Regular.ttf")
);
// parameter : Bold Font
Stylish.getInstance().setFontRegular(
                fontFolder.concat("Rajdhani-Bold.ttf")
);
// parameter : Italic Font
Stylish.getInstance().setFontRegular(
                fontFolder.concat("Rajdhani-Light.ttf"))
);
```

to **use the widget**, is **same** like using **android widget**

**EditText**
``` xml
<com.app.infideap.stylishwidget.view.AEditText
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="@string/editextexample" />
```

**TextView**
``` xml
<com.app.infideap.stylishwidget.view.ATextView
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:paddingLeft="5dp"
android:paddingRight="5dp"
android:text="@string/textviewexamplebold"
android:textAppearance="@style/TextAppearance.AppCompat.Medium"
android:textStyle="bold" />
```

**CheckBox**
``` xml
<com.app.infideap.stylishwidget.view.ACheckBox
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="@string/checkboxexample"/>
```

**RadioButton**
``` xml
<com.app.infideap.stylishwidget.view.ARadioButton
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="@string/radiobuttonexample"/>
```

**Button**
``` xml
<com.app.infideap.stylishwidget.view.AButton
style="@style/Button.Default"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="Default" />
```

**MessageBox**
``` xml
<com.app.infideap.stylishwidget.view.MessageBox
android:id="@+id/message_info"
android:layout_width="match_parent"
android:layout_height="wrap_content"
app:boxBackground="@color/colorSuccess"
app:message="@string/helloworld" />
```

for the **MessageBox**, there are several **new declare-styleable** need to **highlight**,
* app:boxBackground - to set background color, only color allow.
* app:message - to set display text.
* app:textStyle - to set textView style, (normal, bold, italic).
* app:innerPadding - to set inner padding of message box.
* app:innerLeftPadding - to set inner left padding of message box.
* app:innerTopPadding - to set inner top padding of message box.
* app:innerRightPadding - to set inner right padding of message box.
* app:innerBottomPadding - to set inner bottom padding of message box.
* app:drawable - to set textView drawable.
* app:drawablePadding - to set padding of textView drawable.

and for **MessageBox** there are **two kind** of **listener or action mode**,

1) CloseButton, display as close icon
``` java
infoMessageBox.setCloseButton(new View.OnClickListener() {
  @Override
  public void onClick(View v) {
      AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
              .setMessage("Close Button Click!").create();
      dialog.show();

      infoMessageBox.setVisibility(View.GONE);
  }
});
```

2) ActionButton, display as outline button with a text
``` java
warningMessageBox.setActionButton(R.string.learnmore, new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("Warning Action Click!").create();
        dialog.show();
    }
});
```

however, only **one action** can be use for **a MessageBox**


## Contact
For any enquiries, please send an email to tr32010@gmail.com. 

## License

    Copyright 2016-2017 Shiburagi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
