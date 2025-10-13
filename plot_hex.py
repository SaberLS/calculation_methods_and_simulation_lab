import matplotlib.pyplot as plt
import pandas as pd
from decimal import Decimal

import sys

# --- Command-line arguments ---
if len(sys.argv) < 2:
    print("Usage: python plot_hex.py <float_double_file.csv> ")
    sys.exit(1)

csv_file = sys.argv[1]

# --- Read main file (float/double hex) --
df = pd.read_csv(csv_file, sep=",")

# --- Use hex for more precision ----
df["float"] = df["f_hex"].apply(float.fromhex)
df["double"] = df["d_hex"].apply(float.fromhex)
df["big_dec"] = df["big_dec"].apply(Decimal)

# --- Errors ----
df["diff_float"] = df["f_abs_err"].apply(Decimal)
df["diff_double"] = df["d_abs_err"].apply(Decimal)

# --- Plot all three subplots ---
plt.figure(figsize=(10, 10))

# 1 Values
plt.subplot(3, 1, 1)
plt.plot(df["n"], df["float"], 'o-', label="float", color='tab:orange')
plt.plot(df["n"], df["double"], 'x-', label="double", color='tab:blue')
plt.plot(df["n"], df["big_dec"], '-', label="BigDecimal (ref)", color='black', linewidth=2)
plt.title("float, double, BigDecimal")
plt.xlabel("n")
plt.ylabel("xₙ")
plt.legend()
plt.grid(True)

# 2 Absolute differences — double
plt.subplot(3, 1, 2)
plt.plot(df["n"], df["diff_double"], 'x-', color='tab:blue', label="|double - BigDecimal|")
plt.yscale("log")
plt.xlabel("n")
plt.ylabel("|double - BigDecimal| (log)")
plt.title("Double error")
plt.legend()
plt.grid(True, which="both", ls=":")

# 3 Absolute differences — float
plt.subplot(3, 1, 3)
plt.plot(df["n"], df["diff_float"], 'o-', color='tab:orange', label="|float - BigDecimal|")
plt.yscale("log")
plt.xlabel("n")
plt.ylabel("|float - BigDecimal| (log)")
plt.title("Float error")
plt.legend()
plt.grid(True, which="both", ls=":")

plt.tight_layout()
plt.show()
