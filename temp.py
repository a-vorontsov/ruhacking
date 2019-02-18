import mraa
import time

test = mraa.I2c(0)
test.adress()i2c mraa example

for x in range(1, 100):
        print(test.read())
        time.sleep(0.5)