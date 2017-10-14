from Tkinter import *


window = Tk()
window.geometry("1280x720")
window.title("Smart Clock")
#textbox for the date information
date = Text(window, bg="blue", width=90)
date.insert(INSERT, "\n" * 11 + "Date Info")
date.tag_configure("center", justify='center')
date.tag_add("center", 0.0, "end")
date.grid(row=0, column=0)
#textbox for the time information
time = Text(window, bg="red", width=90)
time.insert(INSERT, "\n" * 11 + "Time Info")
time.tag_configure("center", justify='center')
time.tag_add("center", 0.0, "end")
time.grid(row=720, column=0)
#texbox for the weather information
weath = Text(window, bg="yellow", width=90)
weath.insert(INSERT, "\n" * 11 + "Weather Info")
weath.tag_configure("center", justify='center')
weath.tag_add("center", 0.0, "end")
weath.grid(row=0, column=1280)


window.mainloop()
