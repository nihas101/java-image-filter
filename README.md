# SimpleImageFilter [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![Build Status](https://travis-ci.org/nihas101/java-image-filter.svg)](https://travis-ci.org/nihas101/java-image-filter) [![Maintainability](https://api.codeclimate.com/v1/badges/52ccb942b49b280b5f97/maintainability)](https://codeclimate.com/github/nihas101/java-image-filter/maintainability)

A small java program which applies simple filters to images.

[![Click here]()](https://github.com/nihas101/java-image-filter/releases/latest)  for the latest release

## Requirements
**Java 1.8** is required to run this program.

## Build Standalone Distribution

To create a standalone distribution as a zip or tar file, run:

```sh
./gradlew distZip
```
or
```sh
./gradlew distTar
```

The distribution is placed under `build/distributions`.

## How to run the program

To run the program from the distribution, extract the zip or tar file, and run the launch script for your system in the bin folder, by typing:
```sh
./simpleImageFilter
```
into the command-line interface or double-clicking the executable.

## Examples

Source images:

<img src="https://user-images.githubusercontent.com/19901622/28267769-d22242e2-6afb-11e7-88c5-b2194902b977.jpg" width="100"> <img src="https://user-images.githubusercontent.com/19901622/28288101-5370928a-6b3e-11e7-9d61-cb4bfd8e4f0d.png" width="100">

| Filter    | Output   |
| --------  | -------- |
| Grayscale | <img src ="https://user-images.githubusercontent.com/19901622/28271410-b7d23f70-6b08-11e7-9cf1-79aaeb5c9081.png" width="100"> |
| Mirroring | <img src ="https://user-images.githubusercontent.com/19901622/28271464-d5020efe-6b08-11e7-9861-962b8bf9d0eb.png" width="100"> | 
| Mirroring | <img src ="https://user-images.githubusercontent.com/19901622/28271467-d542a14e-6b08-11e7-8422-43c6eac50b2b.png" width="100">|
| Mirroring | <img src ="https://user-images.githubusercontent.com/19901622/28271468-d5443a5e-6b08-11e7-92e8-d4f3d5d1f022.png" width="100"> |
| Mirroring | <img src ="https://user-images.githubusercontent.com/19901622/28271466-d5405934-6b08-11e7-9db4-53e82ab49696.png" width="100"> |
| Mirroring | <img src ="https://user-images.githubusercontent.com/19901622/28271469-d545fcae-6b08-11e7-946f-308b1ae0d282.png" width="100"> | <img src ="https://user-images.githubusercontent.com/19901622/28271465-d53f63e4-6b08-11e7-8857-5efae20533aa.png" width="100"> |
| Blur | <img src ="https://user-images.githubusercontent.com/19901622/28288100-536e74dc-6b3e-11e7-8efd-f2b4d5431bc7.png" width="100"> |
| Motion-blur | <img src ="https://user-images.githubusercontent.com/19901622/28583651-338fba7c-716a-11e7-847f-d5881cbec7aa.png" width="100"> |
| Find edges | <img src ="https://user-images.githubusercontent.com/19901622/28589582-0c4485c8-717f-11e7-9b9f-952ccd7694f2.png" width="100"> |
| Sharpen | <img src ="https://user-images.githubusercontent.com/19901622/28586483-51358516-7174-11e7-8eec-080aa8068b21.png" width="100"> |
| Emphasize edges | <img src ="https://user-images.githubusercontent.com/19901622/28586490-58bb2dae-7174-11e7-9f8b-0e815f9643a9.png" width="100"> |
| Emboss | <img src ="https://user-images.githubusercontent.com/19901622/28588520-211540ae-717b-11e7-9f48-ac7130f225da.png" width="100"> |
| Bumpmap | <img src ="https://user-images.githubusercontent.com/19901622/28588917-5a6631c8-717c-11e7-8631-8de050182f60.png" width="100"> |
| Invert | <img src ="https://user-images.githubusercontent.com/19901622/28689147-dfea3a44-7314-11e7-9f46-7a1083e341c3.png" width="100"> |
