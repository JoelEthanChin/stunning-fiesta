from Tkinter import *


window = Tk()
window.geometry("1280x720")
window.title("Smart Clock")
#textbox for the date information
date = Text(window, bg="blue", width=31, height=7, cursor="dot", font=("calibri", 40))
date.insert(INSERT, "\n" * 3 + "Date Info")
date.tag_configure("center", justify='center')
date.tag_add("center", 0.0, "end")
date.grid(row=0, column=0)
#textbox for the time information
time = Text(window, bg="red", width=31, height=7, cursor="dot", font=("calibri", 40))
time.insert(INSERT, "\n" * 3 + "Time Info")
time.tag_configure("center", justify='center')
time.tag_add("center", 0.0, "end")
time.grid(row=720, column=0)
#texbox for the weather information
weath1 = Text(window, bg="yellow", width=32, height=7, cursor="dot", font=("calibri", 40))
weath1.insert(INSERT, "\n" * 3 + "weather Info")
weath1.tag_configure("center", justify='center')
weath1.tag_add("center", 0.0, "end")
weath1.grid(row=0, column=1280)

weath2 = Text(window, bg="yellow", width=32, height=7, cursor="dot", font=("calibri", 40))
weath2.insert(INSERT, "\n" * 3 + "weather Info")
weath2.tag_configure("center", justify='center')
weath2.tag_add("center", 0.0, "end")
weath2.grid(row=720, column=1280)
# Hello guys



window.mainloop()
