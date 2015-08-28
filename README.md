# Appturbo Android Technical Test

Welcome! This repository contains the exercise project for your interview.

Read the contents carefully as it will help you set everything up.

In this exercise, you will have to write a small Android application using the Android Framework and eventually some third-party library

## Getting started

### Prerequisites

This project requires the usual Android Stack stack:

- Java JDK 1.6/1.7
- Android Studio
- Gradle
- Android support Library v22.2.0
- Android Build Tools v22.0.1

### Install the project

Use the "Check out Project from Version Control" in Android Studio and clone the code from Github.

## Instructions

This exercice aims to implement a small Android Application with a list of applications and an About page.

### Context

The major goal of this application is to show a list of applications in order to download them.
The second goal is to introduce the company and yourself.

### Model

This project contains only one Model used to represent one Application, it contains some values :

- name
- description
- logo
- screenshot
- id

### Endpoint

This project is linked to only one endpoint which returns the list of applications in a JSON file
```json
    [
      {
        "name": "",
        "description": "",
        "logo": "",
        "screenshot": "",
        "id": ""
      }
    ]
```

The url of this endpoint is : http://ligol.free.fr/Android/data.json

Feel free to try the apk sample which will show you the application. You can find it at the root of the git repository.

### Steps

- Load the correct Fragment in the AppturboTestActivity
- Make the Application model parcelable
- Develop the list adapter in ListApplicationFragment
- Load the data in ListApplicationFragment
- Initialize the AboutFragment with the correct view and data
- Develop the layout for the DetailActivity according this screenshot
![alt text](./DetailActivity.png?raw=true "DetailActivity Screenshot")
- Load the DetailActivity with the correct data
- Translate the @string/my_description in english and another language(french)

### How tu return your test?

In order to return your test for review, you only need to make a pull request on this repo and use a message like [Return]GithubLogin-Name.
