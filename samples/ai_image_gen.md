# AI Image Generator

There are several ways to replicate an image in BrainGFX. One way to do this is to get the colour of every pixel of the image that you want to replicate and then write the colour to each corresponding pixel on the BrainGFX screen one by one.

But this approach always leads to long, boring pieces of code. There are often better ways to replicate an image. One of the most amazing way to replicate the image is to exploit the regularity in the image.

Here's an example. Consider the swiss flag given below.

![unedited swiss flag](https://raw.githubusercontent.com/inversioncc/BrainGFX/master/samples/image_files/swiss_flag.png)

The tedious way to replicate this flag can be found [here](https://github.com/inversioncc/BrainGFX/blob/master/samples/swiss_flag.md). The code is 5596 characters long!

The better way would be to split the flag into identical smaller sub-parts as below. Now all you have to do is draw the sub-part 1 and then rest of the parts can be draw by just using rotation (changing mode) and repeating the code. This will lead to considerable reduction in code length.

![edited swiss flag](https://raw.githubusercontent.com/inversioncc/BrainGFX/master/samples/image_files/ai_image_gen_swiss_flag.png)

For the flag above, it rather easy to find such a regularity because there are not many elements in the picture. But for a complex image like the one below (image is by Ricardo Cancho Niemietz),

![macau](https://upload.wikimedia.org/wikipedia/commons/3/3b/RGB_3bits_palette_sample_image.png)

It is much difficult for humans to find regularities in such a complicated image. This is where neural networks can come into play. Can you design a neural network which can idenity such regularities and then automatically write a BrainGFX code exploiting these regularities?

This method of finding regularities can also be employed to effectively compress an image.
