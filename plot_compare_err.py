from decimal import Decimal
import matplotlib.pyplot as plt
import pandas as pd
import sys

# --- Command-line arguments ---
if len(sys.argv) < 3:
    print("Usage: python compare_errors.py <results_1.csv> <results_2.csv>")
    sys.exit(1)

file1 = sys.argv[1]
file2 = sys.argv[2]

# --- Read main file --
df1 = pd.read_csv(file1)
df2 = pd.read_csv(file2)

# --- Convert to Decimals ---
df1["d_abs_err_f"] = df1["d_abs_err"].apply(Decimal)
df2["d_abs_err_f"] = df2["d_abs_err"].apply(Decimal)
df1["f_abs_err_f"] = df1["f_abs_err"].apply(Decimal)
df2["f_abs_err_f"] = df2["f_abs_err"].apply(Decimal)

# --- double ---
plt.figure(figsize=(12,6))

plt.subplot(2, 1, 1)
plt.plot(df1["n"], df1["d_abs_err_f"], 'x-', color='tab:blue', label=f'|double - BigDecimal| x_n+3x_n(1-x_n)')
plt.plot(df2["n"], df2["d_abs_err_f"], 'o-', color='tab:orange', label=f'|double - BigDecimal| 4x_n-3x_n^2')
plt.xlabel("n")
plt.ylabel("'|double - BigDecimal| (log)")
plt.yscale("log")
plt.legend()
plt.grid(True, which="both", ls=":")

# --- float ---
plt.subplot(2, 1, 2)
plt.plot(df1["n"], df1["f_abs_err_f"], 'x-', color='tab:blue', label=f'|float - BigDecimal| x_n+3x_n(1-x_n)')
plt.plot(df2["n"], df2["f_abs_err_f"], 'o-', color='tab:orange', label=f'|float - BigDecimal| 4x_n-3x_n^2')
plt.xlabel("n")
plt.ylabel("|float - BigDecimal| (log)")
plt.yscale("log")
plt.legend()
plt.grid(True, which="both", ls=":")
plt.tight_layout()
plt.show()
