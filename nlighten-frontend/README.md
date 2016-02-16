# Nlighten frontend

Angular 1.x based frontend for Nlighten. 

## Installation

Make sure you have `node` and `npm` installed. After cloning the repo, go to `nlighten-frontend` and execute the following:

```shell
# install all dependencies
$ npm install
# start the server with live reload 
$ npm start
```

## Scripts

Scripts are predefined tasks that can be run via `npm run [script]`, for example: `npm run test`.

* `build` - generate a minified build to dist folder
* `dev` - start development server, try it by opening `http://localhost:8080/`
* `test` - run all tests
* `test:live` - continuously run unit tests watching for changes

## Getting started

This project uses `webpack` for module loading and resource transformation. Running `npm start` or `npm run dev` will start a `webpack-dev-server` instance that does the following: 

* compile, process and bundle all dependencies of the project
* start a server at [http://localhost:8080]() with the version of our app
* watch for any changes in your files and reload the browser automatically

For more info in webpack see the [official page](http://webpack.github.io/). 
For more info on Angular head over to [Angular.com](https://angularjs.org/)


## Testing 

TODO add more info on Angular testing, Karma, etc.


