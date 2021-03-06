const axios = require("axios");
const fs = require("fs");

const image = fs.readFileSync("YOUR_IMAGE.jpg", {
  encoding: "base64",
});

var dataset_name = "your-dataset"; // Set Dataset Name (Found in Dataset URL)
var api_key = ""; // Enter your API key here

axios({
  method: "POST",
  url: "https://api.roboflow.com/dataset/" + dataset_name + "/upload",
  params: {
    api_key: api_key,
    name: "YOUR_IMAGE.jpg",
    split: "train",
  },
  data: image,
  headers: {
    "Content-Type": "application/x-www-form-urlencoded",
  },
})
  .then(function (response) {
    console.log(response.data);
  })
  .catch(function (error) {
    console.log(error.message);
  });
