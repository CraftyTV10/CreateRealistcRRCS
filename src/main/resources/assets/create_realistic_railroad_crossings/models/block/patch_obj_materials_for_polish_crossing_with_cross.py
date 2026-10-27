"""
Patch a Blockbench-exported OBJ so the signal light objects reference
custom material names (used for neoforge_TintIndex via the MTL file).

Edit CONFIG below, then run:  python patch_obj_materials.py
Blockbench names objects with spaces ("light left"); OBJ material names
use underscores ("light_left") to match the MTL file.
"""

# ----------------------------- CONFIG -----------------------------
INPUT_OBJ  = "polish_signal_with_cross1.obj"
OUTPUT_OBJ = "polish_signal_with_cross1_patched.obj"

# OBJ object name (in 'o <name>' lines)  ->  material name for 'usemtl'
MATERIAL_FOR_OBJECT = {
    "light left":  "light_left",    # -> neoforge_TintIndex 0
    "light right": "light_right",   # -> neoforge_TintIndex 1
}
# -------------------------------------------------------------------

def main():
    with open(INPUT_OBJ, "r", encoding="utf-8") as f:
        lines = f.read().splitlines()

    out_lines = []
    current_obj = None
    replaced = 0

    for line in lines:
        if line.startswith("o "):
            current_obj = line[2:].strip()
        elif line.startswith("usemtl") and current_obj in MATERIAL_FOR_OBJECT:
            new_mat = MATERIAL_FOR_OBJECT[current_obj]
            print(f"  [{current_obj}] {line}  ->  usemtl {new_mat}")
            out_lines.append(f"usemtl {new_mat}")
            replaced += 1
            continue
        out_lines.append(line)

    if replaced != len(MATERIAL_FOR_OBJECT):
        print(f"WARNING: replaced {replaced} materials, expected "
              f"{len(MATERIAL_FOR_OBJECT)} -- check the object names in CONFIG.")

    with open(OUTPUT_OBJ, "w", encoding="utf-8") as f:
        f.write("\n".join(out_lines) + "\n")
    print(f"Done: wrote {OUTPUT_OBJ} ({replaced} material(s) reassigned).")

if __name__ == "__main__":
    main()