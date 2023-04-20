#ifndef bovee_ssd_h
#define bovee_ssd_h

class SSD {
  public:
    SSD(int a, int b, int c, int d, int e, int f, int g, int h);
    void begin();
    void set_display(int val);
  private:
    int _a, _b, _c, _d, _e, _f, _g, _h;
    int _pin_iter[];
};

#endif
