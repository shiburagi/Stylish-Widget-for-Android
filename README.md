# RESTLibrary
This library I use in my previous and current android project and may got an issue and error. 
I will keep improve this library until it stable and useful.

![Screenshot](https://github.com/shiburagi/RESTLibrary-BETA-/blob/master/screenshot/device-2016-03-03-172807.png)

## Features
 * set custom font for almost all view.
 * Message Box

Android 7.0+ support

## Download
 * **JAR** : (https://drive.google.com/file/d/0Bw_drx3o3plaMWlLaVBXOXcwZWc/view?usp=sharing)
 * **APK** : (https://drive.google.com/file/d/0Bw_drx3o3plaZzlLaHZzTDEtYVk/view?usp=sharing) 

## Including In Your Project
This library is presented as a `.jar` file which you can include in the `libs/`
folder of your application. You can download the latest version from the
[google drive](https://drive.google.com/file/d/0Bw_drx3o3plaMWlLaVBXOXcwZWc/view?usp=sharing).

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

to use the widget, is same like using android widget

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

for the **MessageBox**, there are several **new decalre-styleable** need to **highlight**,
* app:boxBackground - to set background color, only color allow.
* app:message - to set display text.
* app:textStyle - to set textView style, (normal, bold, italic).
* app:innerPadding - to set inner padding of message box.
* app:innerPadding - to set inner padding of message box.
* app:innerPadding - to set inner padding of message box.
* app:innerPadding - to set inner padding of message box.

``` java
RESTManager.getInstance().setTimeout(miliseconds);
```

This library include **GSON** in **dependency**, which means you can **send request in object representation(POJO)** rather than a plain string and **retrieve the response in object representation(POJO)**. Below the example how to use it,

``` java
Login login = new Login(username, password);

RESTManager.getInstance().requestSync(
        new Path(
                null, // null if doesn't have local/dummy data in app
                "http://jsonplaceholder.typicode.com/posts", // null if the REST API not ready yet
                RESTManager.METHOD_POST //method of the request
        ),
        login, // POJO 
        Login.class,
        new RESTManager.OnDataRequestListener<Login>() { // Interface
            /**
             * if the process is complete without any error.
             * @param object
             */
            @Override
            public void onSuccess(Login object) {
            }
            /**
             *  if an error occurred
             * @param e
             */
            @Override
            public void onFailed(Exception e) {
            }
            /**
             * if the device doesn't have internet connection
             */
            @Override
            public void onNoIntenetConnection() {
            }
            /**
             * This method always execute.
             * @param success
             */
            @Override
            public void finish(boolean success) {
            }
        }

);
```
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
