import mraa
import sock1# Attack
attackButton = mraa.Gpio(29)
attackButton.dir(mraa.DIR_IN)

# Other One
otherButton = mraa.Gpio(31)
otherButton.dir(mraa.DIR_IN)

# Inventory Button
invButton = mraa.Gpio(33)
invButton.dir(mraa.DIR_IN)

# Movement sensor
moveSensor = mraa.I2c(1)
moveSensor.address(0x4c)
moveSensor.writeReg(0x07, 0x09)

# Looking Sensor
lookSensor = mraa.I2c(0)
lookSensor.address(0x4c)
lookSensor.writeReg(0x07, 0x09)

def NormalPlayerCode(TCP_IP, TCP_PORT):
    while True:
        sentMess = ""
        attackInt = int(attackButton.read())
        otherInt = int(otherButton.read())
        inventoryInt = int(invButton.read())
        inputs = 
        [
            str(bin(moveSensor.readReg(0x00)))[2:], 
            str(bin(moveSensor.readReg(0x01)))[2:], 
            str(bin(moveSensor.readReg(0x02)))[2:]
        ]

        if(attackInt == 1):
            sentMess+="L"
        elif(otherInt == 1):
            sentMess+="R"
        
        if(inventoryInt == 1):
            sentMess+="I"
        
        sentMess+=calcUpDown(inputs[0])
        sentMess+=calcLeftRight(inputs[2])

        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        s.connect((TCP_IP, TCP_PORT))
        s.send(sentMess)
        s.close()

        print("sent" + sentMess)
        time.sleep(0.25)

def calcUpDown(inp):
    lengthses = len(inp)
    if((lengthses <= 4) and (int(inp) > 18):
        return "W"
    elif((lengthses <= 5) and (int(inp[0:4]) > 18):
        return "S"
    return ""

def calcLeftRight(inp):
    lengthses = len(inp)
    if((lengthses <= 4) and (int(inp) > 10):
        return "A"
    elif((lengthses <= 5) and (int(inp) > 10):
        return "D"
    return ""

def calcLUpDown(inp):
    lengthses = len(inp)
    if((lengthses <= 4) and (int(inp) > 10):
        return "U"
    elif((lengthses <= 5) and (int(inp) > 10):
        return "J"
    return ""


def calcLLeftRighr(inp):
    lengthses = len(inp)
    if((lengthses <= 4) and (int(inp) > 10):
        return "H"
    elif((lengthses <= 5) and (int(inp) > 10):
        return "K"
    return ""

NormalPlayerCode('192.168.43.57', 11011)
