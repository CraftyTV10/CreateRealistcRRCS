#!/usr/bin/env python3
"""
Patch a Blockbench-exported OBJ so objects reference clean material names
(used for neoforge_TintIndex and textures via the MTL file).
"""

from pathlib import Path

# Resolve paths relative to this script's folder (models/block/)
BASE_DIR = Path(__file__).resolve().parent

# ----------------------------- CONFIG -----------------------------
INPUT_OBJ  = BASE_DIR / "polish_signal_with_cross_1_old.obj"
OUTPUT_OBJ = BASE_DIR / "polish_signal_with_cross_1.obj"

# Lowercase OBJ object name -> material name for 'usemtl'
MATERIAL_FOR_OBJECT = {
    "light left":  "light_left",    # -> neoforge_TintIndex 0
    "light right": "light_right",   # -> neoforge_TintIndex 1
}

# Fallback material for all other objects (replaces Blockbench's m_<uuid>)
DEFAULT_MATERIAL = "housing"
# -------------------------------------------------------------------

def main():
    if not INPUT_OBJ.exists():
        raise FileNotFoundError(f"Input OBJ not found: {INPUT_OBJ}")

    with INPUT_OBJ.open("r", encoding="utf-8", errors="replace") as f:
        lines = f.read().splitlines()

    out_lines = []
    current_obj = None
    replaced = 0

    for line in lines:
        stripped = line.strip()
        if stripped.startswith("o "):
            current_obj = stripped[2:].strip().lower()
        elif stripped.startswith("usemtl "):
            if current_obj in MATERIAL_FOR_OBJECT:
                new_mat = MATERIAL_FOR_OBJECT[current_obj]
                replaced += 1
            else:
                new_mat = DEFAULT_MATERIAL
            print(f"  [{current_obj}] {stripped}  ->  usemtl {new_mat}")
            out_lines.append(f"usemtl {new_mat}")
            continue
        out_lines.append(line)

    if replaced != len(MATERIAL_FOR_OBJECT):
        print(
            f"WARNING: replaced {replaced} light materials, expected "
            f"{len(MATERIAL_FOR_OBJECT)} -- check object names in CONFIG."
        )

    # Write with Unix LF (\n) line endings
    with OUTPUT_OBJ.open("w", encoding="utf-8", newline="\n") as f:
        f.write("\n".join(out_lines) + "\n")

    print(f"Done: wrote {OUTPUT_OBJ.name} ({replaced} light material(s) reassigned).")

if __name__ == "__main__":
    main()
