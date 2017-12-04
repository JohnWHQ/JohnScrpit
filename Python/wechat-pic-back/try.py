import re
with open("try.dat", 'rb') as f:
    buf = bytearray(f.read())

magic = 0xff ^ list(buf)[0] if buf else 0x00 #important
imgfile = re.sub(r'.dat$', '.jpg', "try.dat")
with open(imgfile, 'wb') as f:
    newbuf = bytearray(map(lambda b: b ^ magic, list(buf)))
    f.write(str(newbuf))