import pandas as pd
import matplotlib.pyplot as plt
import sys


if len(sys.argv) < 3:
    print("Usage: python plot_epsilon_steps.py <epsilon_float_steps.csv> <epsilon_double_steps.csv>")
    sys.exit(1)

file_float = sys.argv[1]
file_double = sys.argv[2]

# --- Read data ---
df_float = pd.read_csv(file_float)
df_double = pd.read_csv(file_double)

df_float["epsilon"] = df_float["epsilon"].astype(float)
df_double["epsilon"] = df_double["epsilon"].astype(float)


# --- Float vs Double ---
plt.figure(figsize=(12, 6))
plt.plot(df_float["iteration"], df_float["epsilon"], 'o-', color='tab:orange', label="float ε")
plt.plot(df_double["iteration"], df_double["epsilon"], 'x-', color='tab:blue', label="double ε")
plt.yscale("log")
plt.xlabel("n")
plt.ylabel("current value")
plt.title("compare float and double epsilon")
plt.grid(True, which="both", ls=":")
plt.legend()
plt.tight_layout()
plt.show()
