# Predict Vaccination Jakarta

Author:
* Irwan Sanjaya (A1341666)
* Alan Luthfi (M0040339)
* Aknanta Akmal (C1751816)
* Rizaldo Rizky Himawansah (A0040344)
* Itania Widyanti Rahayu (M1161487)

## Dataset Overview ##

We will use Vaccination dataset from https://corona.jakarta.go.id/id which is a institution in Jakarta that monitoring vaccination process in Jakarta. You can find the dataset in [here](https://tiny.cc/Datacovidjakarta)

## Machine Learning Implementation ##
### Step 1. Load Dataset
import file csv from google drive, dataset has been uploaded to google drive and need to be downloaded to jupyter notebook.
![Screenshot_1](https://user-images.githubusercontent.com/70088542/121328276-6388d800-c93e-11eb-8a3f-a4f709e2a3f0.png)

### Step 2. Load dataset to dataframe
using pandas libraries to laod csv file to make it as dataframe

### Step 3. Using date and vaksinasi 1 total data to be processed and drop another column
for machine learning model we use "vaksinasi 1 total" column as dataset

### Step 4. Split training and test to 80% and 20% of dataset
split dataset from value of "vaksinasi1 total" column to 80% training and 20% as test

### Step 5. Using TimeSeriesGenerator
training a machine learning model requires the data to be in {features,target} format. so, this will need to convert the given data into this format using TimeSeriesGenerator.

### Step 6. Train the model
using architecture of LSTM units trained using sgd optimizer and Mean Squared Loss function for 100 epochs. using model.fit_generator() because already created a data generator.

![Screenshot_2](https://user-images.githubusercontent.com/70088542/121330538-4e14ad80-c940-11eb-965f-f040a2d2004e.png)

The layer are starts from LSTM Layer which consist of input data time_step same as look_back and input unit of 1.
The dense layer Perform matrix multiplication to result in an output matrix with a desired last dimension to be 1.

### Step 7. After training, ploting the prediction
After training model, let see if it performed well. testing the model on testing data and see if the prediction and the actual values overlap, then plot it.
![newplot](https://user-images.githubusercontent.com/70088542/121330880-97fd9380-c940-11eb-8eed-a753ac05ae65.png)

### Step 8. Save as json file
Saving the prediction result, data train, and data test as json, to be deployed by cloud computing and accessed by android

## Android Deployment
Android application that display a machine learning model that can predict how long it will take for the vaccination target number to be achieved.
The data is from web API.

Libraries used:
* Retrofit: https://github.com/square/retrofit
* GSON: https://github.com/google/gson
* Spark from Robinhood: https://github.com/robinhood/spark
* Ticker from Robinhood: https://github.com/robinhood/ticker

Vaxi application can be download and develop in Android Studio. 
### Step 1. UI/UX
First step is to design the UI/UX of the app, we decided to show the vaccination data in the form of a graphic and also numbers. Our apps is simple and only consist of homepage.
### Step 2. Creating Layout
Next step to realize the UI/UX design into android application layout in Android Studio. We use text view to display number of texts, sparkview (a library by robinhood) to display the graph, radiogroup and radiobutton to display the selection between data, and tickerview (library develop by robinhood) to show animation in the textview. For now we only focus on the main features of the apps, to show the vaccination prediction.
### Step 3. Coding the App
First, we retrieve the API using Retrofit and GSON. And then we call the data in the Main Activity. We also code the adapter for the SparkView, so it will show the correct data according the user selection. We also add scrubbing feature, so the user can view the data in different dates by scrub the graph.
For the appearance, we also edit the graphic line color by selection. And also add the tickerview code to show animation in the textview. 
This app can display the vaccination prediction data based on user selection in the form of graph.
Here is the some images of how our application looks like.

![WhatsApp Image 2021-06-09 at 17 57 14](https://user-images.githubusercontent.com/78996136/121343713-309a1080-c94d-11eb-9847-6c88b6e74c85.jpeg)
![WhatsApp Image 2021-06-09 at 17 54 43](https://user-images.githubusercontent.com/78996136/121343717-31cb3d80-c94d-11eb-83e0-73a0821f5512.jpeg)
![WhatsApp Image 2021-06-09 at 17 55 30](https://user-images.githubusercontent.com/78996136/121343719-3263d400-c94d-11eb-9fa4-606667d7c988.jpeg)

