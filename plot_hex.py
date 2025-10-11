import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
from decimal import Decimal
import sys

# --- Command-line arguments ---
if len(sys.argv) < 3:
    print("Usage: python plot_hex_bigdec.py <float_double_file.csv> <bigdec_file.csv>")
    sys.exit(1)

csv_file = sys.argv[1]
bigdec_file = sys.argv[2]

# --- Read main file (float/double hex) ---
df = pd.read_csv(csv_file, sep=",")
df["f"] = df["f_hex"].apply(float.fromhex)
df["d"] = df["d_hex"].apply(float.fromhex)

# --- Read BigDecimal reference file ---
try:
    df_big = pd.read_csv(bigdec_file, sep=",")
except pd.errors.ParserError:
    df_big = pd.read_csv(bigdec_file, sep=",", names=["n", "bigdec"])

if "bigdec" not in df_big.columns:
    df_big.columns = ["n", "bigdec"]

df_big["big"] = df_big["bigdec"].apply(Decimal)

# --- Merge datasets by 'n' using OUTER join ---
df_all = pd.merge(df, df_big[["n", "big"]], on="n", how="left")

# --- Compute absolute differences (only where big exists) ---
def safe_diff(a, b):
    if pd.isna(b):
        return np.nan
    return abs(Decimal(str(a)) - b)

df_all["diff_f"] = df_all.apply(lambda row: safe_diff(row["f"], row["big"]), axis=1)
df_all["diff_d"] = df_all.apply(lambda row: safe_diff(row["d"], row["big"]), axis=1)

# Convert to float for plotting
df_all["diff_f_float"] = df_all["diff_f"].astype(float)
df_all["diff_d_float"] = df_all["diff_d"].astype(float)
df_all["big_float"] = df_all["big"].astype(float)

# --- Plot all three subplots ---
plt.figure(figsize=(10,10))

# 1 Values
plt.subplot(3, 1, 1)
plt.plot(df_all["n"], df_all["d"], label="double", marker='x')
plt.plot(df_all["n"], df_all["f"], label="float", marker='o')
plt.plot(df_all["n"], df_all["big_float"], label="BigDecimal (up to n=25)", color="black", linewidth=2)
plt.xlabel("n")
plt.ylabel("Value")
plt.title("Float / Double vs BigDecimal Reference")
plt.legend()
plt.grid(True)

# 2 Absolute differences — double
plt.subplot(3, 1, 2)
plt.plot(df_all["n"], df_all["diff_d_float"], label="|double - BigDecimal|", marker='x', color='tab:blue')
plt.xlabel("n")
plt.ylabel("Abs Diff")
plt.yscale("log")
plt.title("Double vs BigDecimal (log scale)")
plt.legend()
plt.grid(True)

# 3 Absolute differences — float
plt.subplot(3, 1, 3)
plt.plot(df_all["n"], df_all["diff_f_float"], label="|float - BigDecimal|", marker='o', color='tab:orange')
plt.xlabel("n")
plt.ylabel("Abs Diff")
plt.yscale("log")
plt.title("Float vs BigDecimal (log scale)")
plt.legend()
plt.grid(True)

plt.tight_layout()
plt.show()
