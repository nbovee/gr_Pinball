# code based upon https://stackoverflow.com/a/66892766

# target = "through_fire_flames.wav"
target = "darude.wav"
out_name = "song"
final_sample_rate = 6#200/60*10
max_array_bytes = 32767
data_bytes = 2

from textwrap import dedent
import numpy as np
from scipy.fft import *
from scipy.io import wavfile
from scipy.stats import norm
from math import ceil

def sample_to_hz(dataSlice, sampleRate):
    # Fourier Transform
    N = len(dataSlice)
    yf = rfft(dataSlice)
    xf = rfftfreq(N, 1 / sampleRate)

    # Get the most dominant frequency and return it
    return int(xf[np.argmax(np.abs(yf))])

# Open the file and convert to mono
sr, data = wavfile.read(target)

if data.ndim > 1:
    data = np.mean(data, axis=1) # convert to mono, leave negatives intact

hz_data = []
am_data = []
subsample = int(sr/final_sample_rate)
for i in range(0,len(data)-subsample, subsample):
# Return a slice of the data from start_time to end_time
    dataToRead = data[i:i+subsample]
    am = np.average(np.absolute(dataToRead))
    am_data.append(int(am))
    hz = sample_to_hz(dataToRead, sr)
    hz_data.append(int(abs(hz)))
    
num_array = ceil(len(hz_data)*data_bytes/max_array_bytes)
print(f"Number of arrays needed: {num_array}")
if num_array > 1:
    hz_data = np.array_split(hz_data, num_array)
else:
    hz_data = [hz_data]

# make main header
with open(out_name + '.h', 'w') as f:
    f.write(dedent(f'''\
        #include "{out_name}_hz.h" //frequency data
        #define {target} // original song name
        #define FILE_SAMPLE_RATE {int(final_sample_rate)} //timing info
    '''))
    for i, array in enumerate(hz_data):
        f.write(dedent(f'''\
            #define NUM_ELEMENTS_{i} {len(array)} //array iterator
        '''))

# make frequency header
with open(out_name + '_hz.h', 'w') as f:
    for i, array in enumerate(hz_data):
        arr_str = np.array2string(np.asarray(array),separator=",\n")[1:-1]
        f.write(dedent(f'''\
            const int track_hz{i}[] PROGMEM = {'{'}'''))
        for e in array:
            f.write(f"{e},\n")
        f.write('};')

print("done")
