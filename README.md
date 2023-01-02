# LearnYourEnvironment

## Introduction

Learn Your Environment - Animals is developed for children who are in the age of distinguishing their environment. This app aims to teach children about the animals that are either domestic or wild. Children can also practice to pronounce the animal name and see an estimated score based on their pronuncitaion.

## About HUAWEI ML Kit - Automatic Speech Recognition

Automatic speech recognition (ASR) can recognize speech not longer than 60s and convert the input speech into text in real time. This service uses industry-leading deep learning technologies to achieve a recognition accuracy of over 95%.

For more information -> [HUAWEI ML Kit - Automatic Speech Recognition Guide](https://developer.huawei.com/consumer/en/doc/development/hiai-Guides/ml-asr-0000001050066212) 

## About HUAWEI ML Kit - Text to Speech

Text to speech (TTS) can convert text information into audio output in real time. Rich timbres are provided and the volume and speed can be adjusted, thereby natural voices can be produced.

For more information -> [HUAWEI ML Kit - Text to Speech](https://developer.huawei.com/consumer/en/doc/development/hiai-Guides/ml-tts-0000001050068169)

## About Huawei ML Kit - Custom Model Text Classification

It allows us to extract values ​​by grouping the texts, while doing this, certain classifications are used. Ml kit allows us to create your own custom model with AI Create

### Before the start

#### 1- Developer Account
You should have an AppGallery Console developer account.If you do not have an account,you should [create an account](https://id1.cloud.huawei.com/CAS/portal/userRegister/regbyphone.html).

#### 2-  HMS Toolkit
For using ai create that hms tool kit must be installed.
Android Studio: File → Settings → Plugins → HMS Toolkit 
! IDE must be restarted after installation
![](https://miro.medium.com/max/828/0*Ud0OGt6L7RWwAe5w)

Important: HMS Toolkit is expected to support Android Studio Bumblebee version from June 2022. It is currently available in the Android Arctic Fox version.

#### 3- Python for ML Kit
AI Creator requires Python v3.7.5 for ML Kit use.Complete your installation by downloading the Python 3.7.5 version from the [Link](https://www.python.org/downloads/release/python-375/).
##### Notes:
- Automatically select the process of adding the path during installation
![](https://miro.medium.com/max/828/1*A9rRNhjK1SuB7EsMpL-QnA.webp)
- Make sure to install with pip selected during installation.
- The path may conflict if there is another python version already installed. You have to uninstall the old version and install the new one.
- If Android Studio is open after installing Python, it must be restarted.

#### 4- Data Preparation
You can use two types of text data in HMS ML Kit Text Classification.
- Training text in JSON format: The JSON text format is [{“label”: “ “, “text”: “ “},{“label”: “ “, “text”: “ “}] <br/>
[{“label”: “category1”, “text”: “sample text”},
{“label”: “category2”, “text”: “sample text”},
{“label”: “category3”, “text”: “sample text”}]
- Training text in TXT format: Classify the TXT files by category. <br/>
![](https://miro.medium.com/max/640/1*K-vJto1WFKQpnEx8m5P2TQ.webp)
- You should separate dataset as % 80 train data and % 20  test data

### Project Steps 

#### 1- Create Android Project

Let’s start with creating an empty android project. <br/>
Notes: The min sdk for text classification should be 22.

#### 2- App Gallery Connection:
2.1- A project is created from [My Projects](https://developer.huawei.com/consumer/en/service/josp/agc/index.html#/myProject) -> Add new project by going to the [App Gallery Connect Console](https://id5.cloud.huawei.com/CAS/portal/loginAuth.html). <br/>
2.2- Enable data processing location <br/>
![](https://miro.medium.com/max/640/1*1Vr83dD0N0nsTre7ZyBOhQ.webp) <br/>
2.2- Adding App to Project <br/>
2.3- [Integrating the AppGallery Connect SDK](https://developer.huawei.com/consumer/en/doc/development/AppGallery-connect-Guides/agc-get-started#h1-1577962129917) in Android Studio: <br/>
SDKs are added to the gradle file before the agconnect-services.json file (because we haven’t activated the ML kit service yet).<br/>
a) Project-level build.gradle <br/>
allprojects {<br/>
 repositories {<br/>
 // Add the Maven address.
 maven {url ‘https://developer.huawei.com/repo/'}
 }
} <br/>
… <br/>
buildscript{<br/>
 repositories {<br/>
 // Add the Maven address.
 maven { url ‘https://developer.huawei.com/repo/' }
 }
 <br/>
 dependencies {<br/>
 // Add dependencies.
 classpath ‘com.huawei.agconnect:agcp:1.5.2.300’
 }
} <br/>

b) App-level build.gradle <br/>

dependencies {
 // Add dependencies.<br/>
 implementation ‘com.huawei.agconnect:agconnect-core:1.5.2.300’
}<br/>
…<br/>
// Add the information to the bottom of the file.<br/>
apply plugin: ‘com.huawei.agconnect’ <br/>

2.4- Enable ML Kit Service <br/>
My projects → Project → Build → ML Kit (in left menu) <br/>
![](https://miro.medium.com/max/828/1*_iqvK2WhXcL305-L_9-Oug.webp)<br/>

2.5- Add agconnect-services.json
top left menu - project settings → app information → download agconnect-services.json file
Then add it on your project to app directory in project view
Then click enable now button <br/>
![](https://miro.medium.com/max/828/1*ly5Jhi79SRcdteHlyLg7Ng.webp) <br/>

#### 3 Model Creation
##### Requirements:

- The number of text categories is at least 2
- Each category contains at least 20 files. More files will guarantee a higher model precision.
- The text size does not exceed 10 MB.
- The file encoding format is UTF-8.
##### 3.1- Coding Assistant 
If you have installed android studio you should see hms plugin. <br/>
HMS → Coding Assistant → AI → AI Create → Text Classification <br/>
![](https://miro.medium.com/max/828/1*qVaZ9l0JIwBP3Cy8ejPvaA.webp)<br/>
Notes: Afterwards, you may see such a screen. <br/>
![](https://miro.medium.com/max/640/1*TztwPn_sV5CFb9xL0ENQEQ.webp)<br/>
After clicking the ok button here, it will ask you to choose a path and save the mindspore file (.whl) to the location you selected.
- Open CMD from your computer and go to the path where the mindspore file is "cd Mindspore_Path" <br/>
- then install it with pip "pip install Mindspore_filename"
![](https://miro.medium.com/max/786/1*aamInZYry9-tngPRRV-F0w.webp)<br/>

##### 3.2- Creating Model
Here we add the dataset we created. <br/>
![](https://miro.medium.com/max/828/1*e9WnZfwzVxQY2T55lHtS4Q.webp)<br/>

- Currently, only Chinese and English are supported.
- When you press the Case1 and Case 2 options, you can obtain a sample data set.
- Case1: IMDB dataset (json format), Case2: News Categories (TXT format)
- If you are experienced in this field, you can change the iteration count and learning rate values with Advanced settings.
##### 3.3- Testing Model
You can see the accuracy rate in the logs when train and validate are 100%
![](https://miro.medium.com/max/750/1*x6MZn7BlFxJnXQQt-_Mn_g.webp) <br/>
![](https://miro.medium.com/max/828/1*vWTis6j_pdn-KVqLN5e57w.webp)<br/>
We will upload the test model we have prepared to test the model we have created.<br/>
- When you press the Case1 and Case 2 options, you can obtain a sample data set. <br/>
End the test process, measure the average accuracy of the test. <br/>

#### 4 Add The Created Model To The Application
##### 4.1- Add Dependency
implementation 'com.huawei.hms:ml-computer-model-executor:2.1.0.300'<br/>
implementation 'mindspore:mindspore-lite:5.0.5.300'<br/>
implementation 'com.huawei.hms:base:6.1.0.300'<br/>
##### 4.1- Add Model File
Add .aar file in libs <br/>
![](https://miro.medium.com/max/622/1*d7fch_roJETMrk-FzjY02g.webp)<br/>
implementation files('libs/ms-textclassifier-2.0.0.aar') <br/>
##### 4.2- Add Ms Extension 
And the following section must be added under the app-level android section for our model file with .ms extension to work.<br/>
android {<br/>
     ...<br/>
    aaptOptions {<br/>
        noCompress "ms", "tflite"<br/>
    }<br/>
}<br/>

##### 4.3- Add Ms Extension In Asset Folder
![](https://miro.medium.com/max/612/1*3b8bv0pCNETACc2dXebZOA.webp)<br/>

##### 4.3- Add Permissions In Manifest Folder

- &lt;uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" /&gt;<br/>
- &lt;uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" /&gt; <br/>
- &lt;uses-permission android:name="android.permission.INTERNET" /&gt; <br/>


## What You Will Need

### Hardware Requirements

* A computer that can run Android Studio.
* Huawei mobile device with a USB data cable for running developed apps

### Software Requirements

* Java JDK 1.8 or later
* Android Studio 2021.2.x
* Android SDK package
* Android API Level 23 or higher
* HMS Core (APK) 5.0.0.300 or later
* EMUI 5.0 or later

## Getting Started

Learn Your Environment uses HUAWEI services. To use them, you have to [create an app](https://developer.huawei.com/consumer/en/doc/distribution/app/agc-help-createapp-0000001146718717) first. Before getting started, please [sign-up](https://id1.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html?service=https%3A%2F%2Foauth-login1.cloud.huawei.com%2Foauth2%2Fv2%2Flogin%3Faccess_type%3Doffline%26client_id%3D6099200%26display%3Dpage%26flowID%3D6d751ab7-28c0-403c-a7a8-6fc07681a45d%26h%3D1603370512.3540%26lang%3Den-us%26redirect_uri%3Dhttps%253A%252F%252Fdeveloper.huawei.com%252Fconsumer%252Fen%252Flogin%252Fhtml%252FhandleLogin.html%26response_type%3Dcode%26scope%3Dopenid%2Bhttps%253A%252F%252Fwww.huawei.com%252Fauth%252Faccount%252Fcountry%2Bhttps%253A%252F%252Fwww.huawei.com%252Fauth%252Faccount%252Fbase.profile%26v%3D9f7b3af3ae56ae58c5cb23a5c1ff5af7d91720cea9a897be58cff23593e8c1ed&loginUrl=https%3A%2F%2Fid1.cloud.huawei.com%3A443%2FCAS%2Fportal%2FloginAuth.html&clientID=6099200&lang=en-us&display=page&loginChannel=89000060&reqClientType=89) for a HUAWEI developer account.

After creating the application, you need to [ generate a signing certificate fingerprint](https://developer.huawei.com/consumer/en/codelab/HMSPreparation/index.html#3). Then you have to set this fingerprint to the application you created in AppGallery Connect.

* Go to "My Projects" in AppGallery Connect.
* Find your project from the project list and click the app on the project card.
* On the Project Setting page, set SHA-256 certificate fingerprint to the SHA-256 fingerprint you've generated.
![](https://camo.githubusercontent.com/bfe121fcbc9248a30405323f43b75b5c4050ade26cff703f01e85f8fec42fd54/68747470733a2f2f636f6d6d756e69747966696c652d6472636e2e6f702e6869636c6f75642e636f6d2f46696c655365727665722f67657446696c652f636d74795075622f3031312f3131312f3131312f303030303030303030303031313131313131312e32303230303531313137343130332e30383937373437313939383738383030363832343036373332393936353135353a35303531303631323038323431323a323830303a363933304144383646334635414636423237343045463636364135363136354536354133374536344641333035413330433545464239393844413338443430392e706e673f6e656564496e697446696c654e616d653d747275653f6e656564496e697446696c654e616d653d747275653f6e656564496e697446696c654e616d653d747275653f6e656564496e697446696c654e616d653d74727565)
* Change the agconnect-services.json file with yours.
* To use ML Kit in your app, you also need to enable the service in [AppGallery Connect](https://developer.huawei.com/consumer/en/service/josp/agc/index.html).
Then, obtain the API key from [AppGallery Connect](https://developer.huawei.com/consumer/en/service/josp/agc/index.html). For details, please refer to [Preparations for ML Kit Service](https://developer.huawei.com/consumer/en/doc/development/hiai-Guides/add-appgallery-0000001050038080)
* Replace the [key](https://github.com/Explore-In-HMS/LearnYourEnvironment/blob/master/app/build.gradle#L51) with the one in your API key.

## Using the Application
Upon completing the essential parts of the code, connect your mobile device to the PC and enable the USB debugging mode. In the Android Studio window, click the icon to run the project you have created in Android Studio to generate an APK. Then install the APK on the mobile device. <br/>
1-) Before you run the app, make sure that you have a working internet connection.<br/>
2-) Then, open the app upon installing it on your device.<br/>
3-) The application will ask you for permission to record audio. Please allow it to use the app with voice commands.<br/>
4-) You see a Welcome Screen that contains the "Play Game" button. <br/>
5-) You see a Animal List Screen that contains the "Animals" and you can choose one of them. <br/>
6-) You see a Animal Detail Screen that contains  "listen dialog","Mic Button" and "Sound Button" <br/> 
7-) You see a Resuld Dialog Screen that contains "Result","Go To Home Button" and "Go to previous Page Button" <br/> 

## Screenshots
![](https://user-images.githubusercontent.com/76018221/210241742-06b6d848-d8a1-4eae-b8f3-c5ce2c131a82.png)<br/>![](https://user-images.githubusercontent.com/76018221/210242246-9a7e9345-3b91-4065-a633-2202efc46deb.png)  <br/> ![](https://user-images.githubusercontent.com/76018221/210241374-43bdee6d-9e68-41e0-8e89-18f56fdbe86b.png)<br/>![](https://user-images.githubusercontent.com/76018221/210242365-05fe3f0e-1689-41e3-8c4a-bca87af251fc.png) <br/>![resultdialog](https://user-images.githubusercontent.com/76018221/210241690-6d04efee-72ab-4b1b-b4a4-f7060f432d9c.png) <br/>
## Project Structure 
Learn Your Environment is designed with MVVM design pattern.
## Libraries
- Huawei ML Kit - Automatic Speech Recognition
- Huawei ML Kit - Text to Speech
- Huawei ML Kit - Custom Model Text Classification
- ViewModel
- Navigation
- ViewBinding
- Dagger Hilt
- Lottie
## Contributor
[Ertug Sagman](https://github.com/ertug-sagman0) <br/>
[Ataberk Celiktas](https://github.com/AtaberkCeliktas)
