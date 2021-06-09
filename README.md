# Predict Vaccination Jakarta

Author:
* Irwan Sanjaya (A1341666)
* Alan Luthfi (M0040339)
* Aknanta Akmal(C1751816)
* Rizaldo Rizky Himawansah (A0040344)
* Itania Widyanti Rahayu (M1161487)


## Dataset Overview

We will use Vaccination dataset from https://corona.jakarta.go.id/id which is a institution in Jakarta that monitoring vaccination process in Jakarta. You can find the dataset in [here](https://tiny.cc/Datacovidjakarta)

## Machine Learning Implementation

![Screenshot_0](https://user-images.githubusercontent.com/70088542/121327316-89fa4380-c93d-11eb-942b-6f3a50412705.png)
![Screenshot_1](https://user-images.githubusercontent.com/70088542/121327366-9383ab80-c93d-11eb-859d-2d7398808ebc.png)
![Screenshot_2](https://user-images.githubusercontent.com/70088542/121327369-941c4200-c93d-11eb-9c39-38a57a8ba997.png)
![Screenshot_3](https://user-images.githubusercontent.com/70088542/121327371-941c4200-c93d-11eb-9879-5a938c4b8887.png)
![Screenshot_4](https://user-images.githubusercontent.com/70088542/121327372-94b4d880-c93d-11eb-850b-0e73150d96b5.png)
![Screenshot_5](https://user-images.githubusercontent.com/70088542/121327376-954d6f00-c93d-11eb-8512-a1c98894ac54.png)
![Screenshot_6](https://user-images.githubusercontent.com/70088542/121327379-954d6f00-c93d-11eb-84f6-430ba57a1c45.png)
![Screenshot_7](https://user-images.githubusercontent.com/70088542/121327380-95e60580-c93d-11eb-8183-9c6a280f47c9.png)
![Screenshot_8](https://user-images.githubusercontent.com/70088542/121327381-95e60580-c93d-11eb-9d32-d3d411bd9fa3.png)
![Screenshot_9](https://user-images.githubusercontent.com/70088542/121327382-967e9c00-c93d-11eb-8217-a953294abd4e.png)
![Screenshot_10](https://user-images.githubusercontent.com/70088542/121327385-97173280-c93d-11eb-91b8-8e0f768ee36c.png)
![Screenshot_11](https://user-images.githubusercontent.com/70088542/121327386-97173280-c93d-11eb-9374-f2bddecc6398.png)


## Android Implementation
Android application that display a machine learning model that can predict how long it will take for the vaccination target number to be achieved.
The data is from web API.

Libraries used:
Retrofit: https://github.com/square/retrofit
GSON: https://github.com/google/gson
Spark from Robinhood: https://github.com/robinhood/spark
Ticker from Robinhood: https://github.com/robinhood/ticker

Android Deployment
Vaxi application can be download and develop in Android Studio. 
Step 1. UI/UX
First step is to design the UI/UX of the app, we decided to show the vaccination data in the form of a graphic and also numbers. Our apps is simple and only consist of homepage.
Step 2. Creating Layout
Next step to realize the UI/UX design into android application layout in Android Studio. We use text view to display number of texts, sparkview (a library by robinhood) to display the graph, radiogroup and radiobutton to display the selection between data, and tickerview (library develop by robinhood) to show animation in the textview. For now we only focus on the main features of the apps, to show the vaccination prediction.
Step 3. Coding the App
First, we retrieve the API using Retrofit and GSON. And then we call the data in the Main Activity. We also code the adapter for the SparkView, so it will show the correct data according the user selection. We also add scrubbing feature, so the user can view the data in different dates by scrub the graph.
For the appearance, we also edit the graphic line color by selection. And also add the tickerview code to show animation in the textview. 
This app can display the vaccination prediction data based on user selection in the form of graph.
Here is the some images of how our application looks like.

![photo_2021-06-02_17-01-02](https://user-images.githubusercontent.com/78996136/120471682-35037e00-c3cf-11eb-8b92-e3b4cbcecdc3.jpg)
![photo_2021-06-02_17-01-08](https://user-images.githubusercontent.com/78996136/120471705-3cc32280-c3cf-11eb-83f8-5442b745bc3d.jpg)
