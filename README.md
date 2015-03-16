# GenUS #
A visualization tool for non coding RNA secondary structures.

It was originally planned to extend this tool and implement algorithms that matches an unknown secondary structure with a known primary structure.


## Setup the development environment ##
  * Install [Eclipse](http://www.eclipse.org/downloads/) (Eclipse IDE for Java Developers)
  * Install [Google Web Toolkit (GWT)](http://www.gwtproject.org/) plugin for Eclipse

## Get the project from the archive or clone it ##
  * Download and extract the [archive](https://github.com/mgermain/genus-genetic-profiling-tool/releases/tag/v1.0) and create a new Eclipse Java Project from the extracted folder
  * Or `git clone https://github.com/mgermain/genus-genetic-profiling-tool`


## To run the project in "development mode" ##
  * Eclipse->Run as-> web application : it should output an URL `http://localhost:8888/GenUS.html?gwt.codesvr=132.210.47.18:9997`
  * Open the URL in a browser (it will request the Google Web Toolkit Developer plugin)


## To run the project in "compiled mode" (standalone) ##
  * Eclipse->GWT compile project
  * Open GenUS\war\GenUS.html
