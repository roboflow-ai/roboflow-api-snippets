import requests
import base64
import io
from PIL import Image

# Load Image with PIL
image = Image.open("YOUR_IMAGE.jpg").convert("RGB")

# Convert to JPEG Buffer
buffered = io.BytesIO()
image.save(buffered, quality=90, format="JPEG")

# Base 64 Encode
img_str = base64.b64encode(buffered.getvalue())
img_str = img_str.decode("ascii")

# Your API Key
api_key = ""
# Set Dataset Name (Found in Dataset URL)
dataset_name = "your-dataset"

# Construct the URL
upload_url = "".join([
    "https://api.roboflow.com/dataset/"+ dataset_name + "/upload",
    "?api_key=" + api_key,
    "&name=YOUR_IMAGE.jpg",
    "&split=train"
])

# POST to the API
r = requests.post(upload_url, data=img_str, headers={
    "Content-Type": "application/x-www-form-urlencoded"
})

# Output result
print(r)