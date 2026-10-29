FAQ and help (only took 30 hours of R&D to make this)
If texture UV looks distorted go into a 3D viewer like Babylon.js.
If invert Y must be enabled to make the model work then add the flag:
```
"flip_v":true
``` 
inside the json.

If the model is off center use:
```
"transform": {
    "translation": [0.5, 0.0, 0.5]
}
```