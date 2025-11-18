from PIL import Image
import os

COLOR_MAP = {
    (34, 177, 76): 0,     # grass
    (40, 40, 40): 1,      # wall 
    (0, 100, 0): 2,       # tree
    (120, 120, 120): 3,   # rubble1
    (180, 180, 180): 4,   # rubble2
    (139, 69, 19): 5,     # mud
    (200, 140, 20): 6,    # door
    (160, 82, 45): 7,     # chest
    (0, 120, 255): 8,     # water
    (240, 230, 140): 9,   # sand
}


def color_to_value(rgb):
    if rgb in COLOR_MAP:
        return COLOR_MAP[rgb]
    return COLOR_MAP[min(COLOR_MAP.keys(), key=lambda c: sum((a-b)**2 for a, b in zip(c, rgb)))]

def image_to_array(image_path):
    img = Image.open(image_path).convert("RGB")
    w, h = img.size
    return [[color_to_value(img.getpixel((x, y))) for x in range(w)] for y in range(h)]

def save_map(array, output_path):
    with open(output_path, "w") as f:
        for row in array:
            f.write(" ".join(map(str, row)) + "\n")

# === usage ===
image_path = r"C:\dev\TheTenthDoor\src\res\maps\mapImage\level2.png"
array = image_to_array(image_path)
output_file = os.path.splitext(image_path)[0] + ".txt"
save_map(array, output_file)

print(f"exported to {output_file}")
