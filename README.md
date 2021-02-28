# AES128-Encryptor-Decryptor-KeyGenerator

You can also check the [blog](https://alimoghimii.github.io/AES128-Encryptor-Decryptor-KeyGenerator/) for updates! (powered using Github pages)

This is an AES-128 bit Encryption and Decryption algorithm written in Java ,it also contains a Key Generator which uses the diffie-hellman solution
## Table of Content

[General Info](https://github.com/AliMoghimii/AES128-Encryptor-Decryptor-KeyGenerator#General-Info)

[Input and output](https://github.com/AliMoghimii/AES128-Encryptor-Decryptor-KeyGenerator#Inputs-and-Outputs)

[Licensing](https://github.com/AliMoghimii/AES128-Encryptor-Decryptor-KeyGenerator#Licensing)

### General Info:

        The source codes are available in /src :

            The Encryption Algorithm is Part1.java
            The Decryption Algorithm is Part2.java
            The Key Generator is Part3.java

        The codes can be compiled by any java compiler and IDE

### Inputs and Outputs:

The code only allows **inputs** in hexadecimal 128 bit format and prints **outputs** in a hexadecimal 128 bit format.
it is expected for the input and output to be given/printed as the following:

**Part1 (Encryption):**

    Input :
    The Key : a 128 bit hexadecimal value
    The Plain text : a 128 bit hexadecimal value
    
    Example :
    c0000000000000000000000000000000 00000000000000000000000000000000
    
    Output :
    The Cipher text : a 128 bit hexadecimal value
    
    Example :
    4bc3f883450c113c64ca42e1112a9e87
    
**Part2 (Decryption):**

Input :
    The Key : a 128 bit hexadecimal value
    The Cipher text : a 128 bit hexadecimal value
    
    Example :
    80000000000000000000000000000000 0edd33d3c621e546455bd8ba1418bec8
    
    Output :
    The Plain text : a 128 bit hexadecimal value
    
    Example :
    00000000000000000000000000000000

TODO : check if the input value is a 128 bit hex value (until then check the validity of the number manually)

**The Key and Plain Text for all of the rounds is also being printed and can be seen (Part1)**

TODO : printing the cipher text for each decryption round isnt implemented

**Part3 (Key Gen) :**
this [website](https://www.irongeek.com/diffie-hellman.php) assisted me in writing this algorithm 

    the input is given as :
    p , g , a , b (instructions given in the link above)
    and the output is printed as :
    result seen by person A , result seen by person B , f Key
    
### Licensing:

AliMoghimii/AES128-Encryptor-Decryptor-KeyGenerator is licensed under the GNU General Public License v3.0
