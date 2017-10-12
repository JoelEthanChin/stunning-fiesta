from Tkinter import *

window = Tk()
window.geometry("1280x720")
window.title("Smart Clock")

t = Text(window)
t.insert(INSERT, "Hello")
t.insert(END, "Bye Bye.....")
t.pack(side = LEFT)

window.mainloop()
