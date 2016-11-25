# ShimmerHelper
![image](https://github.com/killer8000/ShimmerHelper/blob/master/app/src/main/res/drawable/shimmerhelper.gif)

ShimmerHelper for android. It is very simple to use .Button,EditText,TextView is ok.
eg:
```
        EditText et = (EditText) findViewById(R.id.et);
        ShimmerHelper helper2 = new ShimmerHelper(et, et.getPaint());
        helper2.setDuration(3000);
        helper2.setMode(Shader.TileMode.REPEAT);
        helper2.setColors(Color.parseColor("#ff00ff"), Color.parseColor("#00ffff"));
        helper2.start();
        

