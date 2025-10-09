import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
import sys

# Check if file name was given
if len(sys.argv) < 2:
    print("Use: python plot_hex.py <nazwa_pliku.csv>")
    sys.exit(1)

# File name is a first argument given by user
csv_file = sys.argv[1]

# Read CSV
df = pd.read_csv(csv_file, sep=",")

# Convert hex
df["f"] = df["f_hex"].apply(float.fromhex)
df["d"] = df["d_hex"].apply(float.fromhex)

# Compute absolute difference
df["diff"] = np.abs(df["d"] - df["f"])

# Create figure
plt.figure(figsize=(8,6))

# Subplot 1: f_hex vs d_hex
plt.subplot(2, 1, 1)
plt.plot(df["n"], df["f"], marker='o', label="f_hex")
plt.plot(df["n"], df["d"], marker='x', label="d_hex")
plt.xlabel("n")
plt.ylabel("Value (double)")
plt.title("f_hex vs d_hex")
plt.legend()
plt.grid(True)

# Subplot 2: absolute difference
plt.subplot(2, 1, 2)
plt.plot(df["n"], df["diff"], marker='s', color='red')
plt.xlabel("n")
plt.yscale("log")
plt.ylabel("|Δ| = |d_hex - f_hex|")
plt.title("Absolute Difference between d_hex and f_hex")
plt.grid(True)

plt.tight_layout()
plt.show()
